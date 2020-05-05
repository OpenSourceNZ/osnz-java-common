package a.b;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Entity
public class NewTestDomain {

    @Column
    public String body;
}
