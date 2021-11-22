package webcrawler.prueba.dao;

import org.apache.ibatis.annotations.Mapper;
import webcrawler.prueba.model.Transaction;

@Mapper
public interface TransactionDao {

    public Integer create(Transaction transaction);

    public Integer getLastInsertId();
}
