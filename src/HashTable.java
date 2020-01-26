import java.util.LinkedList;

public class HashTable
{
    private LinkedList<Entry>[] arr;
    private int size;
    private int capacity;

    public HashTable()
    {
        this(101);
    }
    public HashTable(int newSize)
    {
        arr = new LinkedList[newSize];
        capacity = newSize;
        size = 0;
    }
    int size()
    {
        return size;
    }
    Object remove(Entry e) {return remove(e.getKey());}
    Object remove(Object key)
    {
        int idx = Math.abs(key.hashCode()) % capacity;

        if(arr[idx] == null) return null;

        for(int i = 0; i < arr[idx].size(); i++)
        {
            Entry e = arr[idx].get(i);

            if(e.getKey().equals(key))
            {
                arr[idx].remove(i);
                size--;


                if(arr[idx].size() == 0)
                    arr[idx] = null;

                return e;
            }
        }

        return null;
    }

    public Object put(Entry e) {return put(e.getKey(), e.getValue());}
    public Object put(Object key, Object value) throws IllegalStateException
    {

        if(size == capacity) throw new IllegalStateException("thing full");

        int idx = Math.abs(key.hashCode()) % capacity;

        Object before = arr[idx];
        if(arr[idx] == null) arr[idx] = new LinkedList<>();

        //check if copy exists
        for(int i = 0; i < arr[idx].size(); i++)
        {
            Entry e = arr[idx].get(i);

            if(e.getKey().equals(key))
            {
                return before;
            }
        }

        arr[idx].add(new Entry(key, value));
        size++;

        return before;
    }

    Object get(Entry e){return get(e.getKey());}
    Object get(Object key)
    {
        int idx = Math.abs(key.hashCode()) % capacity;

        if(arr[idx] == null) return null;

        //check if copy exists
        for(int i = 0; i < arr[idx].size(); i++)
        {
            Entry e = arr[idx].get(i);

            if(e.getKey().equals(key))
            {
                return e.getValue();
            }
        }

        return null;
    }
    public double avgLen()
    {
        double len = 0;
        for(int i = 0; i < capacity; i++)
        {
            if(arr[i] != null) len += arr[i].size();
        }

        return len / (double) capacity;
    }

    public double maxLen()
    {
        double len = 0;
        for(int i = 0; i < capacity; i++)
        {
            if(arr[i] != null) len = Math.max(len, arr[i].size());
        }

        return len;
    }



    public String toString()
    {
        String  s = "";

        for(int i = 0; i < arr.length; i++)
        {
            if(arr[i] != null)
            {
                for(int j = 0; j < arr[i].size(); j++)
                {

                    String idx = Integer.toString(i);

                    for (int k = 0, size = idx.length(); k < 3 - size; k++)
                        idx = "0" + idx;

                    s += idx + ": " + arr[i].get(j) + "\n";
                }
            }
        }
        return s;
    }
}