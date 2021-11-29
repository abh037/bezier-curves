package jrtr;

import javax.vecmath.Vector3f;

public interface Bezier {
	
	public Vector3f[] getPoints(int n);
	public Vector3f[] getTangents(int n);

}
