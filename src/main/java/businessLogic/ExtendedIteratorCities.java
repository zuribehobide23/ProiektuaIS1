package businessLogic;

import java.util.List;

public class ExtendedIteratorCities implements ExtendedIterator<String> {

    private int pos;
    private List<String> departingCities;

    public ExtendedIteratorCities(List<String> departingCities) {
        this.pos = 0;
        this.departingCities = departingCities;
    }

    @Override
    public boolean hasNext() {
        return this.pos < this.departingCities.size(); 
    }

    @Override
    public String next() {
        if (hasNext()) {
        	 return this.departingCities.get(this.pos++); 
        }
        return null;
    }

    @Override
    public String previous() {
        if (hasPrevious()) {
        	return this.departingCities.get(--this.pos);
        }
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return this.pos > 0; 
    }

    @Override
    public void goFirst() {
        this.pos = 0; 
    }

    @Override
    public void goLast() {
        this.pos = this.departingCities.size(); 
    }
}


