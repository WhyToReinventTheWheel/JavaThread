package com.mk.fork;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FindMaxValueApp {
	static final int ARRAY_SIZE = 10000;
	public static int THREASHOLD = 0;
	
	public static void main(String[] args) {
		long[] nums = initializeNums();
		THREASHOLD =  nums.length / Runtime.getRuntime().availableProcessors();
		System.out.println("THREASHOLD:"+THREASHOLD +"Runtime.getRuntime().availableProcessors()=" +Runtime.getRuntime().availableProcessors());
		
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		MaximumFindTask findTask = new MaximumFindTask(nums, 0, nums.length);

		System.out.println("Max: " + forkJoinPool.invoke(findTask));
	}

	
	private static class MaximumFindTask extends RecursiveTask<Long> {

		private long[] nums;
		private int lowIndex;
		private int highIndex;

		public MaximumFindTask(long[] nums, int lowIndex, int highIndex) {
			this.highIndex = highIndex;
			this.lowIndex = lowIndex;
			this.nums = nums;
		}

		@Override
		protected Long compute() {

			if (highIndex - lowIndex < FindMaxValueApp.THREASHOLD) {
				return sequentialMaxFinding();
			} else {

				int middleIndex = (lowIndex + highIndex) / 2;

				MaximumFindTask leftSubtask = new MaximumFindTask(nums, lowIndex, middleIndex);
				MaximumFindTask rightSubtask = new MaximumFindTask(nums, middleIndex, highIndex);

				invokeAll(leftSubtask, rightSubtask);

				return Math.max(leftSubtask.join(), rightSubtask.join());
			}
		}

		private long sequentialMaxFinding() {

			long max = nums[0];

			for (int i = lowIndex; i < highIndex; ++i)
				if (nums[i] > max)
					max = nums[i];

			return max;
		}
	}


	
	private static long[] initializeNums() {
		
		Random random = new Random();
		
		long[] nums = new long[ARRAY_SIZE];
		
		for(int i=0;i<ARRAY_SIZE;++i)
			nums[i] = random.nextInt(100);
		
		return nums;
	}
}
