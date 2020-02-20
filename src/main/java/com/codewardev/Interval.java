package com.codewardev;

//https://www.codewars.com/kata/52b7ed099cdc285c300001cd/train/java

import java.util.Arrays;
import java.util.Comparator;

public class Interval {

	public static int sumIntervals(int[][] intervals) {
		
		if(intervals == null || intervals.length == 0) return 0;
		
		int currMin;
		int currMax;
		int total = 0;
		int[][] pIntervals = new int[intervals.length][2];
		for(int a=0; a<intervals.length; a++) {
			pIntervals[a][0] = intervals[a][0];
			pIntervals[a][1] = intervals[a][1];
		}
		
		// Need to sort it first
		Arrays.sort(pIntervals, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[0] - o2[0];
			}
			
		});
		
//		for(int i=0; i<pIntervals.length; i++) {
//			System.out.println("first: "+pIntervals[i][0]+" second: "+pIntervals[i][1]);
//		}
		
		
		for(int i=0; i<pIntervals.length; i++) {
			int[] currInterval = pIntervals[i];
			currMin = currInterval[0];
			currMax = currInterval[1];
		//	System.out.println("currMin: "+currMin+" currMax: "+currMax+ " total: "+total);
			if(currMax - currMin == 0) continue;
			
			if(i+1 < pIntervals.length) {
				for(int j=i+1; j<pIntervals.length; j++) {
					System.out.print("first: "+pIntervals[j][0]+" second: "+pIntervals[j][1]);
					if(pIntervals[j][0] >= currMin && pIntervals[j][0] <= currMax
							|| pIntervals[j][1] <= currMax && pIntervals[j][1] >= currMin) {
						System.out.println(" is overlap with "+currMin+" or "+currMax);
						currMin = Math.min(currMin, pIntervals[j][0]);
						currMax = Math.max(currMax, pIntervals[j][1]);
						pIntervals[j][0] = 0;
						pIntervals[j][1] = 0;
					} else
						System.out.println();
				}
			}
		//	System.out.println("currMin: "+currMin+" currMax: "+currMax+" now!");
			total += currMax - currMin;
		}

		System.out.println("result: "+total);
		
		return total;
	}

}
