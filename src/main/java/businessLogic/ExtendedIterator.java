package businessLogic;

import java.util.Iterator;

public interface ExtendedIterator<String> extends Iterator<String>	{
	//return	the	actual	element	and	go	to	the	previous
	public Object	previous();
	//true	if	ther	is	a	previous	element
	public boolean hasPrevious();
	//It	is	placed	in	the	first	element
	public void goFirst();
	//It	is	placed	in	the	last	element
	public void goLast();
}

