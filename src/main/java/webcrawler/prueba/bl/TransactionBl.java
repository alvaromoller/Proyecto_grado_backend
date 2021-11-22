package webcrawler.prueba.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webcrawler.prueba.dao.TransactionDao;
import webcrawler.prueba.model.Transaction;

@Service
public class TransactionBl {

    private TransactionDao transactionDao;

    @Autowired
    public TransactionBl(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public Transaction createTransaction (Transaction transaction) {
        // Registramos la transacción en la Base de Datos
        this.transactionDao.create(transaction);

        // Obtenemos la llave primaria generada
        Integer lastPrimaryKey = this.transactionDao.getLastInsertId();

        transaction.setTxId(lastPrimaryKey);
        return transaction;
    }

}
