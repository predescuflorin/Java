package application;

public class Operation {
	private int nr1;
	private int nr2;
	private int result;
	private String op;
	
	public int getNr1() {
		return nr1;
	}
	public void setNr1(int nr1) {
		this.nr1 = nr1;
	}
	public int getNr2() {
		return nr2;
	}
	public void setNr2(int nr2) {
		this.nr2 = nr2;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public Operation(int nr1, int nr2) {
		super();
		this.nr1 = nr1;
		this.nr2 = nr2;
		this.result = 0;
	}
	
	protected void calculate() {
		if (this.getOp().equals("Adunare")) {
			this.setResult(this.getNr1() + this.getNr2());
		} else if (this.getOp().equals("Scadere")) {
			this.setResult(this.getNr1() - this.getNr2());
		} else if (this.getOp().equals("Inmultire")) {
			this.setResult(this.getNr1() * this.getNr2());
		} else if (this.getOp().equals("Impartire")) {
			if (this.getNr2() == 0) {
				
			}
		}
	}
	
}
