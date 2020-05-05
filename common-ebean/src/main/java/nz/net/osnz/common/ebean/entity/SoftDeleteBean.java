package nz.net.osnz.common.ebean.entity;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
public interface SoftDeleteBean {

    boolean isEnable();

    void setEnable(boolean enable);

}
