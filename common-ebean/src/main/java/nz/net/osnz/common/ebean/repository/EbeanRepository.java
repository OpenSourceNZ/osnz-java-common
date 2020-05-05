package nz.net.osnz.common.ebean.repository;

import io.ebean.EbeanServer;
import io.ebean.Query;
import io.ebean.annotation.Transactional;
import nz.net.osnz.common.ebean.entity.BaseEntity;
import nz.net.osnz.common.ebean.entity.BaseModel;
import nz.net.osnz.common.ebean.entity.SoftDeleteBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public abstract class EbeanRepository<T> {

    private static final Logger LOG = LoggerFactory.getLogger(EbeanRepository.class);

    @Autowired
    private EbeanServer ebeanServer;

    public abstract Class<T> getEntityClass();

    protected EbeanServer getServer() {
        return this.ebeanServer;
    }

    /**
     * Look up an entity by passing a unique ID
     *
     * @param id is the entity unique ID to look up
     * @return the entity bean if the passing ID is valid, otherwise return NULL
     */
    public T getById(Long id) {
        return id != null ? getServer().find(getEntityClass(), id) : null;
    }

    public T findBy(Consumer<Query> consumer) {
        return buildQuery(consumer).findOne();
    }

    /**
     * Find all existing entities from remote server
     *
     * @return a list of entity
     */
    public List<T> findAll() {
        return findAllBy(null);
    }

    /**
     * Find a list of entity by passing a closure that used to set up query
     *
     * @param consumer is a closure to set up the query
     * @return a list of entity
     */
    public List<T> findAllBy(Consumer<Query> consumer) {
        return buildQuery(consumer).findList();
    }

    /**
     * Count all records
     *
     * @return the total size of the entity
     */
    public int countAll() {
        return countAllBy(null);
    }

    /**
     * Count all records by passing a closure
     *
     * @param consumer is a closure to set up the query
     * @return the total size of query result
     */
    public int countAllBy(Consumer<Query> consumer) {
        return buildQuery(consumer).findCount();
    }

    /**
     * a transactional method to persist the entity bean into the remote server
     *
     * @param entity is the entity bean to be persisting
     * @return a map to represent the result and persisted entity
     */
    public T save(T entity) {
        Consumer<T> save = e -> getServer().save(e);
        return safePersist(entity, save);
    }

    /**
     * Delete an entity from remote server in a transactional method
     *
     * @param entity is a record going to be deleted from remote server
     */
    public void persistDelete(T entity) {
        Consumer<T> delete = e -> {
            if (SoftDeleteBean.class.isAssignableFrom(e.getClass())) {
                ((SoftDeleteBean) e).setEnable(false);
                getServer().save(e);
            } else {
                getServer().delete(e);
            }
        };
        safePersist(entity, delete);
    }

    /**
     * a common transactional method to do a passing operation
     *
     * @param entity    is the record need to deal with
     * @param operation is a closure method under a transactional session
     * @return the operation status in current transactional session
     */
    @Transactional
    public T safePersist(T entity, Consumer<T> operation) {
        if (operation != null) {
            try {
                operation.accept(entity);
                getServer().refresh(entity);
                LOG.info("Persist ({}:{}) successfully", getEntityClass().getSimpleName());
                return entity;
            } catch (OptimisticLockException oEx) {
                LOG.error("Transaction Error ({}:{}) because of {}",
                    getEntityClass().getSimpleName(),
                    entity,
                    oEx.getMessage(),
                    oEx);
            } catch (NullPointerException npe) {
                LOG.error("Bean Error ({}:{}) because of {}",
                    getEntityClass().getSimpleName(),
                    entity,
                    npe.getMessage(),
                    npe);
            } catch (PersistenceException pEx) {
                LOG.error("Persist Error ({}:{}) because of {}",
                    getEntityClass().getSimpleName(),
                    entity,
                    pEx.getMessage(),
                    pEx);
            }
        }
        return null;
    }

    protected Query<T> buildQuery(Consumer<Query> consumer) {
        Query<T> query = getServer().find(getEntityClass());
        if (SoftDeleteBean.class.isAssignableFrom(getEntityClass())) {
            query.where().eq("enable", true);
        }
        if (consumer != null) {
            consumer.accept(query);
        }
        return query;
    }

}
