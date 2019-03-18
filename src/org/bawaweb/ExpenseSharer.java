package org.bawaweb;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Set;

public class ExpenseSharer {

	// to store info for whom payment is owed, will receive cash
	private Hashtable<Integer, Double> 	receivePayments = new Hashtable<Integer, Double>();
	
	// to store info for whom payment is due, transfer cash to
	private Hashtable<Integer, Double> 	transferPayments = new Hashtable<Integer, Double>();	


	public double calculateSum(double[] expenses) {
		double sum = 0.0;
		
		for ( int i = 0; i < expenses.length; i++) {
			sum += expenses[i];
		}
		
		return sum;
	}
	
	
	private void process(double[][] expenses) {
		// to store the grand total of all exps
		double theTotalExps 	= 0.0;
		double averagePayment 	= 0.0;
		
		// to store the each individual's payment sums
		ArrayList<Double> 	indvdlPayments = new ArrayList<Double>();			

		for ( int i = 0; i < expenses.length; i++ ) {
			double[] indvdlExps = expenses[i];
			double sumIndvdlExps = calculateSum(indvdlExps);
			
			theTotalExps += sumIndvdlExps;
			indvdlPayments.add(i, sumIndvdlExps);
		}
		
		System.out.println ("\n\n___===================________")	;
		
		System.out.println("Total Exps is " + theTotalExps);
		
		averagePayment = theTotalExps/expenses.length;		
		System.out.println("Avg Exps [to be paid by all] is " + averagePayment);
		
		System.out.println ("_______________________________")	;
		
		assessDifferenceFromAvg(indvdlPayments, averagePayment);
	}
	
	
	private void assessDifferenceFromAvg(ArrayList<Double> indPayments, double averagePayment) {
		
		for ( int i = 0; i < indPayments.size(); i++ ) {
			double payment = indPayments.get(i);
			if ( payment  == averagePayment ) {
				System.out.println("Person " + (i+1) + " paid Average Amount exactly " + payment);
				continue;
			}
			if ( payment > averagePayment) {				
				this.receivePayments.put(i, payment - averagePayment);
				System.out.println("Person " + (i+1) + " is owed / will Receive a total amount of " + (payment - averagePayment));
			} else {
				this.transferPayments.put(i, averagePayment - payment);
				System.out.println("Person " + (i+1) + " OWES / will Transfer a total amount of " + (averagePayment-payment));
				
			}
		}
		
		
		System.out.println ("_______________________________")	;
		reconcilePayments();
	}
	
	
	
	private void reconcilePayments() {
		Set<Integer> transferPaymentsKeySet = this.transferPayments.keySet();
		Set<Integer> receivePaymentsKeySet  = this.receivePayments.keySet();
		
		
		for ( Iterator<Integer> it = transferPaymentsKeySet.iterator(); it.hasNext(); ) {
			int i = it.next();
			double transferAmti = this.transferPayments.get(i);
			
			if (transferAmti == 0.0 )	continue; 
			
			for ( Iterator<Integer> jt = receivePaymentsKeySet.iterator(); jt.hasNext(); ) {
				int j = jt.next();
				double receiveAmtj = this.receivePayments.get(j);
				
				if (transferAmti == 0.0  || receiveAmtj == 0.0)
						continue;
				
				if ( (transferAmti >= receiveAmtj)) {
					System.out.println("Person " + (i+1) + " owes Person " + (j+1)  + " amount " + receiveAmtj);
					
					this.transferPayments.put(i, transferAmti - receiveAmtj);
					this.receivePayments.put(j, 0.0);
				
					transferAmti -= receiveAmtj;
					receiveAmtj = 0.0;
					
				} else {

						System.out.println("Person " + (i+1) + " owes Person " + (j+1)  + " amount " + transferAmti);	
						
						this.transferPayments.put(i, 0.0);
						this.receivePayments.put(j, receiveAmtj-transferAmti);
						
						transferAmti = 0.0;
						receiveAmtj -= transferAmti;
					
				}
				
			}			
			
		}
		
	}
	
	
	public static void main(String[] args) {
		ExpenseSharer expShrr = new ExpenseSharer();
		// 5 people
		double[] asExps = new double[] {10, 20, 10, 18};	//58 jack
		double[] bsExps = new double[] {30, 40, 20};		//90 john
		double[] csExps = new double[] {20, 10, 20, 22};	//72 jim
		double[] dsExps = new double[] {20, 25, 55};		//100 jerry
		double[] esExps = new double[] {10, 0, 10, 20};		//40 joan
		double[][] allExps = new double[][] {asExps, bsExps, csExps, dsExps, esExps};
		
		expShrr.process(allExps);
		
		// 6 people
		double[] aExp = new double[]{90, 100, 200};			// 390, rest all 0
		double[] bExp = new double[]{0};
		double[] cExp = new double[]{0};
		double[] dExp = new double[]{0};
		double[] eExp = new double[]{0};
		double[] fExp = new double[]{0};
		allExps = new double[][]{aExp, bExp, cExp, dExp, eExp, fExp};
		
		expShrr.process(allExps);
		
		// reversing above
		allExps = new double[][]{fExp, bExp, cExp, dExp, eExp, aExp};
		
		expShrr.process(allExps);
		
		
		
		
		double[] aExps = new double[] {10, 20, 10, 40};
		double[] bExps = new double[] {30, 40, 20, 50};
		double[] cExps = new double[] {20, 10, 20, 45};
		double[] dExps = new double[] {20, 25, 30, 55};
		double[] eExps = new double[] {10, 0, 10, 20, 15};
		double[] fExps = new double[] {40, 10, 10, 20, 15, 35};
		double[] gExps = new double[] {40, 15, 55, 35};
		double[] hExps = new double[] {10, 50, 10, 20, 15};
		double[] iExps = new double[] {40, 20, 15, 35};
		
		allExps = new double[][] {aExps, bExps, cExps, dExps, eExps, fExps, gExps, hExps, iExps};
		expShrr.process(allExps);
		
		
		
	}
}
