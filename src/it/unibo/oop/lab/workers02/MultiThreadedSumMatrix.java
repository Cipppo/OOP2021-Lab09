package it.unibo.oop.lab.workers02;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadedSumMatrix implements SumMatrix {

    private final int nthread;
    
    public MultiThreadedSumMatrix(final int nthread) {
        this.nthread = nthread;
    }
    
    private static class Worker extends Thread{
        private final double[][] matrix;
        private final int startpos;
        private final int nelem;
        private long res;
        
        Worker(final double[][] matrix, final int startpos, final int nelem){
            super();
            this.matrix = matrix;
            this.startpos = startpos;
            this.nelem = nelem;
        }
        
        public void run() {
            System.out.println("Working from position " + this.startpos + " to position "
                    + (this.startpos + this.nelem -1));
            for(int i = this.startpos ; i < matrix.length && i < this.startpos + this.nelem; i++) {
                for(int k = 0; k < matrix.length ; k++) {
                    this.res += matrix[i][k];
                }
            }
        }
        
        
        public long getResult() {
            return this.res;
        }
    }

    @Override
    public double sum(double[][] matrix) {
        final int size = matrix.length % this.nthread + matrix.length / this.nthread;
        
        final List<Worker> workers = new ArrayList<>(nthread);
        for(int start = 0; start < matrix.length ; start += size) {
            workers.add(new Worker(matrix, start, size));
        }
        
        for(final Worker w : workers) {
            w.start();
        }
        
        
        long sum = 0;
        for(final Worker w : workers) {
            try {
                w.join();
                sum += w.getResult();
            }catch(InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
        
        return sum;
        
    }
}
