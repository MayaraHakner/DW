package DAOs;

import Entidades.Status;
import java.util.ArrayList;
import java.util.List;

public class DAOStatus extends DAOGenerico<Status> {

private List<Status> lista = new ArrayList<>();    public DAOStatus(){
        super(Status.class);
    }

    public int autoIdStatus() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idStatus) FROM Status e ").getSingleResult();
        if (a != null) {
            //System.out.println(a);
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Status> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Status e WHERE e.idStatus LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Status> listById(int id) {
        return em.createQuery("SELECT e FROM Status + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<Status> listInOrderNome() {
        return em.createQuery("SELECT e FROM Status e ORDER BY e.nomeStatus").getResultList();
    }

    public List<Status> listInOrderId() {
        return em.createQuery("SELECT e FROM Status e ORDER BY e.idStatus").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Status> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdStatus() + "-" + lf.get(i).getNomeStatus());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOStatus daoStatus = new DAOStatus();
        List<Status> listaStatus = daoStatus.list();
        for (Status produto : listaStatus) {
            System.out.println(produto.getIdStatus()+"-"+produto.getNomeStatus());
        }
    }}