package com.codewardev;


// https://www.codewars.com/kata/52b7ed099cdc285c300001cd/train/java

public class Interval {

	public static int sumIntervals(int[][] intervals) {
		
		if(intervals == null || intervals.length == 0) return 0;
		
		// Need to sort it first
		
		int currMin;
		int currMax;
		int total = 0;
		int[][] pIntervals = intervals;
		for(int i=0; i<pIntervals.length; i++) {
			int[] currInterval = pIntervals[i];
			currMin = currInterval[0];
			currMax = currInterval[1];
			System.out.println("currMin: "+currMin+" currMax: "+currMax+ " total: "+total);
			if(currMax - currMin == 0) continue;
			
			if(i+1 < pIntervals.length) {
				for(int j=i+1; j<pIntervals.length; j++) {
					if(pIntervals[j][0] >= currMin && pIntervals[j][0] <= currMax
							|| pIntervals[j][1] <= currMax && pIntervals[j][1] >= currMin) {
						currMin = Math.min(currMin, pIntervals[j][0]);
						currMax = Math.max(currMax, pIntervals[j][1]);
						pIntervals[j][0] = 0;
						pIntervals[j][1] = 0;
					}
				}
			}
			System.out.println("currMin: "+currMin+" currMax: "+currMax+" now!");
			total += currMax - currMin;
		}

		System.out.println("result: "+total);
		
		return total;
	}

}
