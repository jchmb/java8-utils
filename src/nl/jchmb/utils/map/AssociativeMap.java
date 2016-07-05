package nl.jchmb.utils.map;

import java.util.HashMap;

/**
 * An AssociativeMap is a Map that keeps track of 1:1 relations between instances.
 * Defines "left" and "right" associations.
 */
public interface AssociativeMap<L, R> {
	public L getLeft(R right);
	public R getRight(L left);
	public void put(L left, R right);
	public boolean containsLeft(L left);
	public boolean containsRight(R right);
	public boolean removeLeft(L left);
	public boolean removeRight(R right);
	
	public static <L, R> AssociativeMap<L, R> ofHashMaps() {
		return new DefaultAssociativeMap<>(
				HashMap::new,
				HashMap::new
		);
	}
}
