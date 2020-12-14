package query;

public class QueryImp implements Query {

    private String querySoFar;

    private StringBuilder stringBuilder;

    public QueryImp(){
        querySoFar = "";
        stringBuilder = new StringBuilder();
        stringBuilder.append(querySoFar);
    }

    /**
     *
     * @return Query string so far created by builder
     */
    public String getQueryString() {
        return querySoFar;
    }

    /**
     *
     * @return This
     */
    @Override
    public QueryImp select() throws Exception{
        if (querySoFar.contains("SELECT"))
            throw new Exception("SELECT statement used in wrong order!");
        querySoFar = stringBuilder.append("SELECT ").toString();
        return this;
    }

    @Override
    public QueryImp all() throws Exception{
        if(!querySoFar.contains("SELECT"))
            throw new Exception("SELECT statement missed!");
        querySoFar = stringBuilder.append("* ").toString();
        return this;
    }

    /**
     *
     * @param table Required table name
     * @return This
     * @throws Exception
     */
    @Override
    public QueryImp from(String table) throws Exception {
        if(table == null || table.equals(""))
            throw new Exception("Required parameter missed!");
        if(!querySoFar.contains("SELECT"))
            throw new Exception("FROM statement can't come before SELECT statement.");
        if(this.getSelectedColumns() == null)
            throw new Exception("No column selected!");
        querySoFar = stringBuilder.append("FROM " + table + " ").toString();
        return this;
    }

    private String getSelectedColumns() {
        if (querySoFar.contains("*"))
            return "*";
        return null;
    }
}
