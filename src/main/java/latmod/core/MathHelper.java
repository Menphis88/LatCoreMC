package latmod.core;
import java.util.Random;

import latmod.core.mod.LC;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/** Made by LatvianModder */
public class MathHelper // Converter
{
	public static final Random rand = new Random();
	public static final double RAD = Math.PI / 180D;
	public static final double DEG = 180D / Math.PI;
	public static final double TWO_PI = Math.PI * 2D;
	public static final double HALF_PI = Math.PI / 2D;
	
	public static double sin(double d)
	{ return net.minecraft.util.MathHelper.sin((float)d); }
	
	public static double cos(double d)
	{ return net.minecraft.util.MathHelper.cos((float)d); }
	
	public static double tan(double d)
	{ return sin(d) / cos(d); }
	
	public static double sinFromDeg(double f)
	{ return sin(f * RAD); }
	
	public static double cosFromDeg(double f)
	{ return cos(f * RAD); }
	
	public static double tanFromDeg(double f)
	{ return tan(f * RAD); }
	
	/** atan2 using Vertex */
	public static double atan2(Vertex pos, Vertex tar)
	{ if(pos == null || tar == null) return -1F;
	return Math.atan2(pos.x - tar.x, pos.z - tar.z); }
	
	/** atan using Vertex */
	public static double atan(Vertex pos, Vertex tar)
	{ if(pos == null || tar == null) return -1F;
	return Math.atan(pos.y - tar.y); }
	
	public static double sqrt(double d)
	{
		if(d == 0D) return 0D;
		else if(d == 1D) return 1D;
		else return Math.sqrt(d);
	}
	
	public static double sqrt2sq(double x, double y)
	{ return sqrt(sq(x) + sq(y)); }
	
	public static double sqrt3sq(double x, double y, double z)
	{ return sqrt(sq(x) + sq(y) + sq(z)); }
	
	public static double sq(double f)
	{ return f * f; }
	
	public static double sq(double f, int i)
	{ if(i == 2) return sq(f); double f1 = 1F; for(int j = 0; j < i; j++)
	f1 *= f; return f1; }
	
	public static int power(int f, int n)
	{ int j = 1; for(int i = 0; i < n; i++) j *= f; return j; }

	public static double distSq(double x1, double y1, double z1, double x2, double y2, double z2)
	{ return (sq(x2 - x1) + sq(y2 - y1) + sq(z2 - z1)); }
	
	public static double dist(double x1, double y1, double z1, double x2, double y2, double z2)
	{ return sqrt(distSq(x1, y1, z1, x2, y2, z2)); }
	
	public static Vertex getLook(Vertex v, double yaw, double pitch, double dist)
	{
		if(v == null) v = new Vertex();
		double f = cos(pitch * RAD);
		double x1 = cos(-yaw * RAD + HALF_PI);
        double z1 = sin(-yaw * RAD + HALF_PI);
        double y1 = sin(pitch * RAD);
        v.set(x1 * f * dist, y1 * dist, z1 * f * dist);
        return v;
	}
	
	public static int floor(double d)
	{ return net.minecraft.util.MathHelper.floor_double(d); }
	
	public static int chunk(double d)
	{ return floor(d) >> 4; }
	
	public static int ceil(double d)
	{ return net.minecraft.util.MathHelper.ceiling_double_int(d); }
	
	public static int randomInt(Random r, int min, int max)
	{ return min + r.nextInt(max - min + 1); }
	
	public static double randomDouble(Random r, double min, double max)
	{ return min + r.nextDouble() * (max - min); }

	public static boolean isRound(double d)
	{ return Math.round(d) == d; }
	
	public static int lerpInt(int i1, int i2, double f)
	{ return i1 + (int)((i2 - i1) * f); }
	
	public static double lerp(double f1, double f2, double f)
	{ return f1 + (f2 - f1) * f; }
	
	public static double clamp(double d, double min, double max)
	{ if(d < min) d = min; if(d > max) d = max; return d; }
	
	public static int clampInt(int i, int min, int max)
	{ if(i < min) i = min; if(i > max) i = max; return i; }
	
	public static double[] clamp(double[] d, double min, double max)
	{ for(int i = 0; i < d.length; i++) d[i] = clamp(d[i], min, max); return d; }
	
	public static int[] clampInt(int[] i, int min, int max)
	{ for(int j = 0; j < i.length; j++) i[j] = clampInt(i[j], min, max); return i; }
	
	public static int toIntDecoded(String s)
	{ return Integer.decode(s); }
	
	public static int toInt(String s)
	{ return Integer.parseInt(s.trim()); }
	
	public static double toDouble(String s)
	{ return Double.parseDouble(s.trim()); }
	
	public static int toInt(String s, int def)
	{ try { return toInt(s); } catch(Exception e) { return def; } }
	
	public static double toDouble(String s, double def)
	{ try { return toDouble(s); } catch(Exception e) { return def; } }
	
	public static double toSmallDouble(double f)
	{ long i = (long)(f * 100D); return i / 100D; }
	
	public static boolean canParseInt(String s)
	{
		try { Integer.parseInt(s); return true; }
		catch(Exception e) { } return false;
	}
	
	public static double map(double val, double min1, double max1, double min2, double max2)
	{ return min2 + (max2 - min2) * ((val - min1) / (max1 - min1)); }
	
	public static int mapInt(int val, int min1, int max1, int min2, int max2)
	{ return min2 + (max2 - min2) * ((val - min1) / (max1 - min1)); }
	
	public static final Vertex getMidPoint(double x1, double y1, double z1, double x2, double y2, double z2, double p)
	{ double x = x2 - x1; double y = y2 - y1; double z = z2 - z1; double d = Math.sqrt(x * x + y * y + z * z);
	return new Vertex(x1 + (x / d) * (d * p), y1 + (y / d) * (d * p), z1 + (z / d) * (d * p)); }
	
	public static Vertex getMidPoint(Vertex v1, Vertex v2, double p)
	{ return getMidPoint(v1.x, v1.y, v1.z, v2.x, v2.y, v2.z, p); }
	
	public static boolean isPlural(int i) { String s = "" + i;
	return !(s.endsWith("1") && !s.endsWith("11")); }
	
	public static String getPluralWord(int i, String s, String p)
	{ return isPlural(i) ? p : s; }
	
	public static String formatDouble(double d)
	{
		String s = "" + d;
		if(s.endsWith(".0"))
			s = s.substring(0, s.length() - 2);
		return s;
	}
	
	public static AxisAlignedBB getBox(double cx, double y0, double cz, double w, double y1, double d)
	{ return AxisAlignedBB.getBoundingBox(cx - w / 2D, y0, cz - d / 2D, cx + w / 2D, y1, cz + d / 2D); }
	
	public static final int getRotations(double yaw, int max)
	{ return floor((yaw * max / 360D) + 0.5D) & (max - 1); }
	
	public static ForgeDirection get2DRotation(EntityLivingBase el)
	{
		//int i = floor(el.rotationYaw * 4D / 360D + 0.5D) & 3;
		int i = getRotations(el.rotationYaw, 4);
		if(i == 0) return ForgeDirection.NORTH;
		else if(i == 1) return ForgeDirection.EAST;
		else if(i == 2) return ForgeDirection.SOUTH;
		else if(i == 3) return ForgeDirection.WEST;
		return ForgeDirection.UNKNOWN;
	}
	
	public static ForgeDirection get3DRotation(World w, int x, int y, int z, EntityLivingBase el)
	{ return ForgeDirection.values()[BlockPistonBase.determineOrientation(w, x, y, z, el)]; }
	
	public static final int getRotYaw(int rot)
	{
		if(rot == 2) return 180;
		else if(rot == 3) return 0;
		else if(rot == 4) return 90;
		else if(rot == 5) return -90;
		return 0;
	}
	
	public static final int getRotPitch(int rot)
	{
		if(rot == 0) return 90;
		else if(rot == 1) return -90;
		return 0;
	}
	
	public static MovingObjectPosition rayTrace(EntityPlayer ep, double d)
	{
		double y = ep.posY + ep.getDefaultEyeHeight();
		if(ep.worldObj.isRemote) y -= ep.getEyeHeight();
		Vec3 pos = Vec3.createVectorHelper(ep.posX, y, ep.posZ);
		Vec3 look = ep.getLookVec();
		Vec3 vec = pos.addVector(look.xCoord * d, look.yCoord * d, look.zCoord * d);
		return ep.worldObj.func_147447_a(pos, vec, false, true, false);
	}
	
	public static MovingObjectPosition rayTrace(EntityPlayer ep)
	{ return rayTrace(ep, LC.proxy.getReachDist(ep)); }
	
	public static MovingObjectPosition collisionRayTrace(World w, int x, int y, int z, Vec3 start, Vec3 end, FastList<AxisAlignedBB> boxes)
	{
		if(boxes == null || boxes.isEmpty()) return null;
		
		MovingObjectPosition current = null;
		double dist = Double.POSITIVE_INFINITY;
		
		for(int i = 0; i < boxes.size(); i++)
		{
			AxisAlignedBB aabb = boxes.get(i);
			
			if(aabb != null)
			{
				MovingObjectPosition mop = collisionRayTrace(w, x, y, z, start, end, aabb);
				
				if(mop != null)
				{
					double d1 = mop.hitVec.squareDistanceTo(start);
					if(current == null || d1 < dist)
					{
						current = mop;
						current.subHit = i;
						dist = d1;
					}
				}
			}
		}
		
		return current;
	}
	
	public static MovingObjectPosition collisionRayTrace(World w, int x, int y, int z, Vec3 start, Vec3 end, AxisAlignedBB aabb)
	{
		Vec3 pos = start.addVector(-x, -y, -z);
		Vec3 rot = end.addVector(-x, -y, -z);
		
		Vec3 xmin = pos.getIntermediateWithXValue(rot, aabb.minX);
		Vec3 xmax = pos.getIntermediateWithXValue(rot, aabb.maxX);
		Vec3 ymin = pos.getIntermediateWithYValue(rot, aabb.minY);
		Vec3 ymax = pos.getIntermediateWithYValue(rot, aabb.maxY);
		Vec3 zmin = pos.getIntermediateWithZValue(rot, aabb.minZ);
		Vec3 zmax = pos.getIntermediateWithZValue(rot, aabb.maxZ);
		
		if (!isVecInsideYZBounds(xmin, aabb)) xmin = null;
		if (!isVecInsideYZBounds(xmax, aabb)) xmax = null;
		if (!isVecInsideXZBounds(ymin, aabb)) ymin = null;
		if (!isVecInsideXZBounds(ymax, aabb)) ymax = null;
		if (!isVecInsideXYBounds(zmin, aabb)) zmin = null;
		if (!isVecInsideXYBounds(zmax, aabb)) zmax = null;
		Vec3 v = null;
		
		if (xmin != null && (v == null || pos.squareDistanceTo(xmin) < pos.squareDistanceTo(v))) v = xmin;
		if (xmax != null && (v == null || pos.squareDistanceTo(xmax) < pos.squareDistanceTo(v))) v = xmax;
		if (ymin != null && (v == null || pos.squareDistanceTo(ymin) < pos.squareDistanceTo(v))) v = ymin;
		if (ymax != null && (v == null || pos.squareDistanceTo(ymax) < pos.squareDistanceTo(v))) v = ymax;
		if (zmin != null && (v == null || pos.squareDistanceTo(zmin) < pos.squareDistanceTo(v))) v = zmin;
		if (zmax != null && (v == null || pos.squareDistanceTo(zmax) < pos.squareDistanceTo(v))) v = zmax;
		if (v == null) return null; else
		{
			int side = -1;

			if (v == xmin) side = 4;
			if (v == xmax) side = 5;
			if (v == ymin) side = 0;
			if (v == ymax) side = 1;
			if (v == zmin) side = 2;
			if (v == zmax) side = 3;
			
			return new MovingObjectPosition(x, y, z, side, v.addVector(x, y, z));
		}
	}
	
	private static boolean isVecInsideYZBounds(Vec3 v, AxisAlignedBB aabb)
	{ return v == null ? false : v.yCoord >= aabb.minY && v.yCoord <= aabb.maxY && v.zCoord >= aabb.minZ && v.zCoord <= aabb.maxZ; }
	
	private static boolean isVecInsideXZBounds(Vec3 v, AxisAlignedBB aabb)
	{ return v == null ? false : v.xCoord >= aabb.minX && v.xCoord <= aabb.maxX && v.zCoord >= aabb.minZ && v.zCoord <= aabb.maxZ; }
	
	private static boolean isVecInsideXYBounds(Vec3 v, AxisAlignedBB aabb)
	{ return v == null ? false : v.xCoord >= aabb.minX && v.xCoord <= aabb.maxX && v.yCoord >= aabb.minY && v.yCoord <= aabb.maxY; }
	
	public static MovingObjectPosition getMOPFrom(int x, int y, int z, int s, float hitX, float hitY, float hitZ)
	{ return new MovingObjectPosition(x, y, z, s, Vec3.createVectorHelper(x + hitX, y + hitY, z + hitZ)); }
	
	public static final ForgeDirection getDir(int s)
	{
		if(s >= 0 && s < ForgeDirection.VALID_DIRECTIONS.length)
			return ForgeDirection.VALID_DIRECTIONS[s];
		return ForgeDirection.UNKNOWN;
	}
}