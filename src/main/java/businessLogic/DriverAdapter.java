package businessLogic;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import domain.Driver;
import domain.Ride;

public class DriverAdapter extends AbstractTableModel {
	private Driver d;
	public DriverAdapter(Driver d) {
		this.d = d;
	}
	
	@Override
	public int getRowCount() {
		   return d.getCreatedRides().size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		//a 
		Ride da = d.getCreatedRides().get(rowIndex);
		   switch (columnIndex) {
		    case 0:
		     return da.getFrom();
		    case 1:
		     return da.getTo();
		    case 2:
		     return da.getDate();
		    case 3:
		     return da.getnPlaces();
		    case 4:
		     return da.getPrice();
		    default:
		     return null;
		   }
	}

}
