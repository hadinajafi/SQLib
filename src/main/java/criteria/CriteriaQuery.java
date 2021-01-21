package criteria;

public interface CriteriaQuery<T> {

    CriteriaQuery<T> select();

    CriteriaQuery<T> where();

    CriteriaQuery<T> orderBy();
}
