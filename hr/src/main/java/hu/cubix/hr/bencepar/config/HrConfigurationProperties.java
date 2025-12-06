package hu.cubix.hr.bencepar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigurationProperties {

	private Salary salary = new Salary();

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public static class Salary {

		private Default def = new Default();
		private Smart smart = new Smart();

		public Default getDef() {
			return def;
		}

		public void setDef(Default def) {
			this.def = def;
		}

		public Smart getSmart() {
			return smart;
		}

		public void setSmart(Smart smart) {
			this.smart = smart;
		}

	}

	public static class Default {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}

	public static class Smart {

		private double low;
		private double mid;
		private double high;
		private int highPercent;
		private int midPercent;
		private int lowPercent;

		public double getLow() {
			return low;
		}

		public void setLow(double low) {
			this.low = low;
		}

		public double getMid() {
			return mid;
		}

		public void setMid(double mid) {
			this.mid = mid;
		}

		public double getHigh() {
			return high;
		}

		public void setHigh(double high) {
			this.high = high;
		}

		public int getHighPercent() {
			return highPercent;
		}

		public void setHighPercent(int highPercent) {
			this.highPercent = highPercent;
		}

		public int getMidPercent() {
			return midPercent;
		}

		public void setMidPercent(int midPercent) {
			this.midPercent = midPercent;
		}

		public int getLowPercent() {
			return lowPercent;
		}

		public void setLowPercent(int lowPercent) {
			this.lowPercent = lowPercent;
		}

	}

}
