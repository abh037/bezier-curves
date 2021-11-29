float[] vertices = new float[3*slices*slices];
float[] colors = new float[3*slices*slices];
float[] normals = new float[3*slices*slices];
int[] indices = new int[6*slices*slices];

private void makeSurface(int slices, Bezier b)
		{
			VertexData vertexData = renderContext.makeVertexData(slices*slices);
			
			Vector3f[] curve = b.getPoints(slices);
			Vector3f[] tans = b.getTangents(slices);
			
			for(int i = 0 ; i < slices; i++) {	
				
				
				for(int j = 0; j < slices; j++) {
					
					int interval = slices*i + j;
					float theta = (float)Math.toRadians(i*360/slices);
					
					vertices[3*interval + 0] = curve[j].x*(float)Math.cos(theta);
					vertices[3*interval + 1] = curve[j].x*(float)Math.sin(theta);
					vertices[3*interval + 2] = curve[j].y;
					
					Vector3f tan = new Vector3f(-tans[j].y, tans[j].x, 0);
					tan.normalize();
					normals[3*interval + 0] = -tan.x*(float)Math.cos(theta);
					normals[3*interval + 1] = -tan.x*(float)Math.sin(theta);
					normals[3*interval + 2] = tan.y;
					
					indices[6*interval + 0] = slices*i 					+ j;
					indices[6*interval + 1] = slices*((i + 1) % slices)	+ (j + 1) % slices;
					indices[6*interval + 2] = slices*i 					+ (j + 1) % slices;
					indices[6*interval + 3] = slices*i 					+ j;
					indices[6*interval + 4] = slices*((i + 1) % slices) + j;
					indices[6*interval + 5] = slices*((i + 1) % slices) + (j + 1) % slices;
					
					colors[3*interval + 0] = 1;
					colors[3*interval + 1] = 1;
					colors[3*interval + 2] = 1;		
					
				}
			}
		}
