package jrtr;

import javax.vecmath.Vector3f;

public class BezierSegment implements Bezier {
	
	public Vector3f a;
	public Vector3f b;
	public Vector3f c;
	public Vector3f d;
	
	public BezierSegment(Vector3f a, Vector3f b, Vector3f c, Vector3f d) {
		
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		
	}
	
	public BezierSegment(Vector3f[] ctrlPts) {
		a = ctrlPts[0];
		b = ctrlPts[1];
		c = ctrlPts[2];
		d = ctrlPts[3];
	}
	
	public Vector3f[] getPoints(int n) {
		
		Vector3f points[] = new Vector3f[n];
		
		for (int i = 0; i < n; i++) {
			
			float t = (float)i/(n - 1);
			points[i] = getPoint(a, b, c, d, t);
			
		}
		
		return points;
	}
	
	public static Vector3f getPoint(Vector3f a, Vector3f b, Vector3f c, Vector3f d, float t) {
		
		float one_minus_t = 1f - t;
		
		float coef1 = one_minus_t * one_minus_t * one_minus_t;
		float coef2 = 3 * t * one_minus_t * one_minus_t;
		float coef3 = 3 * t * t * one_minus_t;
		float coef4 = t * t * t;
		
		float x = a.x * coef1 + b.x * coef2 + c.x * coef3 + d.x * coef4;
		float y = a.y * coef1 + b.y * coef2 + c.y * coef3 + d.y * coef4;
		float z = a.z * coef1 + b.z * coef2 + c.z * coef3 + d.z * coef4;
		
		return new Vector3f(x, y, z);
		
	}
	
	public Vector3f[] getTangents(int n) {
		
		Vector3f tans[] = new Vector3f[n];
		
		for (int i = 0; i < n; i++) {
			
			float t = (float)i/(n - 1);
			
			tans[i] = getTangent(a, b, c, d, t);
			
		}
		
		return tans;
	}
	
	public static Vector3f getTangent(Vector3f a, Vector3f b, Vector3f c, Vector3f d, float t) {
		
		float one_minus_t = 1f - t;
		
		float coef1 = 3 * one_minus_t * one_minus_t;
		float coef2 = 6 * t * one_minus_t;
		float coef3 = 3 * t * t;
		
		float x = (b.x - a.x) * coef1 + (c.x - b.x) * coef2 + (d.x - c.x) * coef3;
		float y = (b.y - a.y) * coef1 + (c.y - b.y) * coef2 + (d.y - c.y) * coef3;
		float z = (b.z - a.z) * coef1 + (c.z - b.z) * coef2 + (d.z - c.z) * coef3;
		
		return new Vector3f(x, y, z);
		
	}

}
