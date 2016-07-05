package nl.jchmb.utils.map;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DefaultAssociativeMap<L, R> implements AssociativeMap<L, R> {
	private Map<L, R> leftMap;
	private Map<R, L> rightMap;
	
	public DefaultAssociativeMap(
			Supplier<Map<L, R>> leftSupplier,
			Supplier<Map<R, L>> rightSupplier
	) {
		leftMap = leftSupplier.get();
		rightMap = rightSupplier.get();
	}
	
	@Override
	public L getLeft(R right) {
		return rightMap.get(right);
	}

	@Override
	public R getRight(L left) {
		return leftMap.get(left);
	}

	@Override
	public void put(L left, R right) {
		leftMap.put(left, right);
		rightMap.put(right, left);
	}

	@Override
	public boolean containsLeft(L left) {
		return leftMap.containsKey(left);
	}

	@Override
	public boolean containsRight(R right) {
		return rightMap.containsKey(right);
	}
	
	private boolean remove(L left, R right) {
		return leftMap.remove(left, right);
	}

	@Override
	public boolean removeLeft(L left) {
		R right = leftMap.get(left);
		return remove(left, right);
	}

	@Override
	public boolean removeRight(R right) {
		L left = rightMap.get(right);
		return remove(left, right);
	}
}
