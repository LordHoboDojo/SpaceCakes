public class Entry
{
    private Object key, value;
    private boolean removed;

    public Entry()
    {
        this.key = null;
        this.value = null;
        this.removed = false;
    }
    public Entry(Object key, Object value)
    {
        this.key = key;
        this.value = value;
        this.removed = false;
    }
    public Entry(Entry e)
    {
        this.key = e.key;
        this.value = e.value;
        this.removed = false;
    }
    public Object getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public Entry getCopy()
    {
        Entry e = new Entry(key, value);
        return e;
    }

    public void markRemoved()
    {
        removed = true;
    }

    public boolean isRemoved()
    {
        return removed;
    }

    @SuppressWarnings("unused")
    public String toString()
    {
        if(this == null)
            return "NULL";
        if(removed) return "REMOVED";
        return key + ", " + value;
    }

}