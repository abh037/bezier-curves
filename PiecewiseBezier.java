package jrtr;

import javax.vecmath.*;

public class PiecewiseBezier implements Bezier {
	
	public Vector3f[] ctrlPts;
	
	public PiecewiseBezier(Vector3f[] ctrlPts) {
		this.ctrlPts = new Vector3f[ctrlPts.length];
		for (int i = 0; i < ctrlPts.length; i++) {
			this.ctrlPts[i] = ctrlPts[i];
		}
	}
	
	public Vector3f[] getPoints(int n) {
		
		Vector3f points[] = new Vector3f[n];
		int idx = 0;
		int numSegments = (int)(this.ctrlPts.length - 1)/3;
		int j = 3;
		float currentSegment = 1;
		
		for (int i = 0; i < n; i++) {
			float t = (float)i/(n - 1);
			if (t > currentSegment/numSegments) {
				j += 3;
				currentSegment++;
			}
			points[idx] = BezierSegment.getPoint(
					ctrlPts[j - 3], 
					ctrlPts[j - 2], 
					ctrlPts[j - 1], 
					ctrlPts[j - 0], 
					(t - ((currentSegment - 1)/numSegments)) / (1f / numSegments));
			idx++;
		}
		
		return points;
	}
	
	public Vector3f[] getTangents(int n) {
		
		Vector3f tans[] = new Vector3f[n];
		int idx = 0;
		int numSegments = (int)(this.ctrlPts.length - 1)/3;
		int j = 3;
		float currentSegment = 1;
		
		for (int i = 0; i < n; i++) {
			float t = (float)i/(n - 1);
			if (t > currentSegment/numSegments) {
				j += 3;
				currentSegment++;
			}
			tans[idx] = BezierSegment.getTangent(
					ctrlPts[j - 3], 
					ctrlPts[j - 2], 
					ctrlPts[j - 1], 
					ctrlPts[j - 0], 
					(t - ((currentSegment - 1)/numSegments)) / (1f / numSegments));
			idx++;
		}
		
		return tans;
	}

}


