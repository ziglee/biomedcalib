package net.cassiolandim.biomedcalib.entity;

import java.io.Serializable;

/**
 * @author Cassio Landim
 */
public abstract class BaseEntity<T> implements Serializable, HasId, Comparable<T> {
	
}
