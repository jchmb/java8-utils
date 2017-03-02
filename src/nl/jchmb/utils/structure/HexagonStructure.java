package nl.jchmb.utils.structure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

/**
 * HexagonStructure class. Used to store objects of the generic type T in a convenient manner
 * in a hexagon map. For a given (x, y), the x corresponds to the column number and the y
 * to the row number. The first column/row is 0.
 */
public class HexagonStructure<T> {
	private int size;
	private T[] cells;
	
	@SuppressWarnings("unchecked")
	public HexagonStructure(int size, Class<T> cellClass) {
		this.size = size;
		cells = (T[]) Array.newInstance(cellClass, getTotalHeight(getWidth() - 1));
	}
	
	/**
	 * Get the height of the column at position i.
	 * 
	 * @param i
	 * @return
	 */
	public int getHeight(int i) {
		return (i < size) ? (i + size) : ((2 * size - 2) - i + size);
	}
	
	/**
	 * Get the width of the entire hexagonal structure.
	 * 
	 * @return
	 */
	public int getWidth() {
		return size * 2 - 1;
	}
	
	/**
	 * Get the total left height for a given position i.
	 * 
	 * @param i
	 * @return
	 */
	private int getTotalLeftHeight(int i) {
		if (i >= size) {
			i = size - 1;
		}
		return (i + 1) * size + (i * (i + 1)) / 2;
	}
	
	/**
	 * Get the total right height for a given position i.
	 * @param i
	 * @return
	 */
	private int getTotalRightHeight(int i) {
		if (i < size) {
			return 0;
		}
		i -= size;
		return ((i - 4 * size + 4) * (i + 1)) / -2;
	}
	
	/**
	 * Get the total height for a given position i. This is used
	 * to calculate the offset for the cells array.
	 * 
	 * @param i
	 * @return
	 */
	private int getTotalHeight(int i) {
		return getTotalLeftHeight(i) + getTotalRightHeight(i);
	}
	
	/**
	 * Map a (x,y) position to a one-dimensional offset.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private int getOffset(int x, int y) {
		int offset = y;
		if (x > 0) {
			x--;
			offset += getTotalHeight(x);
		}
		return offset;
		
	}
	
	/**
	 * Get the generic cell object at a given position (x,y).
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public T get(int x, int y) {
		if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight(x)) {
			return null;
		}
		int offset = getOffset(x, y);
		if (offset < 0 || offset >= cells.length) {
			return null;
		}
		return cells[offset];
	}
	
	public T random() {
		return cells[new Random().nextInt(cells.length)];
	}
	
	/**
	 * Set a generic cell object a given position (x,y).
	 * 
	 * @param x
	 * @param y
	 * @param cell
	 */
	public void put(int x, int y, T cell) {
		cells[getOffset(x, y)] = cell;
	}
	
	public int getX(int i) {
		return i;
	}
	
	public float getUnitHeight() {
		return (float) (Math.sin(Math.PI / 3.0d));
	}
	
	private float getHeightOffset(int i) {
		if (i < size) {
			return -((float) i) * getUnitHeight();
		} else {
			return -(getWidth() - ((float) i) - 1) * getUnitHeight();
		}
	}
	
	public float getY(int i, int j) {
		float yOffset = getUnitHeight() * getHeightOffset(i);
		return (((float) j) * getUnitHeight() * 2.0f + yOffset);
	}
	
	public float getUnitDistance() {
		return getUnitHeight() * 2;
	}
	
	public float distance(int i1, int j1, int i2, int j2) {
		float x1, y1, x2, y2;
		
		x1 = getX(i1);
		y1 = getY(i1, j1);
		x2 = getX(i2);
		y2 = getY(i2, j2);
		
		return 
				((float) Math.sqrt(
						Math.pow((x2 - x1), 2) +
						Math.pow((y2 - y1), 2)
				) / 
				(float) Math.sqrt(
						Math.pow(getY(0, 0) - getY(0, 1), 2)
				));
	}
	
	/**
	 * Get the neighbours of the cell at (x,y), if it exists.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Stream<T> neighbours(int x, int y) {
		T top, bottom, topRight, topLeft, bottomRight, bottomLeft;
		ArrayList<T> iterable = new ArrayList<T>();
		
		if ((top = get(x, y - 1)) != null) {
			iterable.add(top);
		}
		if ((bottom = get(x, y + 1)) != null) {
			iterable.add(bottom);
		}
		if ((topRight = get(x + 1, y)) != null) {
			iterable.add(topRight);
		}
		if (x < size - 1) {
			if ((bottomRight = get(x + 1, y + 1)) != null) {
				iterable.add(bottomRight);
			}
		} else {
			if ((bottomRight = get(x + 1, y - 1)) != null) {
				iterable.add(bottomRight);
			}
		}
		
		if ((bottomLeft = get(x - 1, y)) != null) {
			iterable.add(bottomLeft);
		}
		
		if (x < size) {
			if ((topLeft = get(x - 1, y - 1)) != null) {
				iterable.add(topLeft);
			}
		} else {
			if ((topLeft = get(x - 1, y + 1)) != null) {
				iterable.add(topLeft);
			}
		}
		
		return iterable.stream();
	}

	public Stream<T> stream() {
		return Stream.of(cells);
	}
}
