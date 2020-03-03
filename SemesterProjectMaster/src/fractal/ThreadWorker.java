package fractal;

import javax.swing.SwingWorker;

import code.Fractal;
import code.Main;
import gui.UserInterface;

public class ThreadWorker extends SwingWorker<WorkerResult, Void>{

	int thread, numberThreads, escDis, escTime, threads;
	Main m1 = new Main();
	Fractal fractal;
	
	public ThreadWorker(Fractal fractal, int escDis, int escTime, int thread, int numberThreads){
		this.thread = thread;
		this.numberThreads = numberThreads;
		this.escDis=escDis;
		this.escTime=escTime;
		this.fractal=fractal;
	}
	/**
	 * doInBackground calls the createEscTimes method in _ui
	 * with the current thread as a parameter. This generates 
	 * the escape times for this single thread. It then finds 
	 * the start Position in the final array where these 
	 * escape times will be placed. 
	 * 
	 * Returns a WorkerResult with parameters start and results.
	 */
	@Override
	protected WorkerResult doInBackground() throws Exception {
		int[][] results = m1.createEscapeTimes(fractal,escDis,escTime,numberThreads,thread);
		int start = 512/numberThreads*thread;
		return new WorkerResult(start, results);
	}
}
