package com.codewardev;

//https://www.codewars.com/kata/52b7ed099cdc285c300001cd/train/java

import java.util.Arrays;
import java.util.Comparator;

public class Interval {

	public static int sumIntervals(int[][] intervals) {
		
		if(intervals == null || intervals.length == 0) return 0;
		
		int[][] pIntervals = sortedCopyOfIntervals(intervals);
		
		int currMin;
		int currMax;
		int total = 0;
		
		for(int i=0; i<pIntervals.length; i++) {
			int[] currInterval = pIntervals[i];
			if(currInterval[1] - currInterval[0] == 0) continue;
			
			currMin = currInterval[0];
			currMax = currInterval[1];
			
			total += subTotalResetIntervalIfUsed(pIntervals, currMin, currMax, i);
		}
		
		return total;
	}

	private static int[][] sortedCopyOfIntervals(int[][] intervals) {
		// For 2D arrays, intervals.clone() or Arrays.copyOf() will fail to copy contents. Instead it copies addresses.
		// so we need to use traditional copy
		int[][] pIntervals = new int[intervals.length][2];
		for(int a=0; a<intervals.length; a++) {
			pIntervals[a] = intervals[a].clone();
		}
		
		// Need to sort the array
		Arrays.sort(pIntervals, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}

		});
		
		return pIntervals;
	}

	private static int subTotalResetIntervalIfUsed(int[][] pIntervals, int currMin, int currMax, int i) {
		if(i+1 < pIntervals.length) {
			for(int j=i+1; j<pIntervals.length; j++) {
				if(pIntervals[j][0] >= currMin && pIntervals[j][0] <= currMax
						|| pIntervals[j][1] <= currMax && pIntervals[j][1] >= currMin) {
					currMin = Math.min(currMin, pIntervals[j][0]);
					currMax = Math.max(currMax, pIntervals[j][1]);
					pIntervals[j][0] = pIntervals[j][1] = 0;
				}
			}
		}
		return currMax - currMin;
	}

}



//package cw;
//
//import java.util.Arrays;
//import java.util.stream.IntStream;
//
//public class Interval {
//
//    public static int sumIntervals(int[][] intervals) {
//        return intervals == null ? 0 : (int) Arrays.stream(intervals)
//            .flatMapToInt(interval -> IntStream.range(interval[0], interval[1]))
//            .distinct()
//            .count();
//    }
//}
