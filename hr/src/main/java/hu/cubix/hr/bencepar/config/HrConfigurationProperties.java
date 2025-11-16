package hu.cubix.hr.bencepar.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigurationProperties {

	private Raise raise;

	public Raise getRaise() {
		return raise;
	}

	public void setRaise(Raise raise) {
		this.raise = raise;
	}

	public static class Raise {

		private Smart smart;

		public Smart getSmart() {
			return smart;
		}

		public void setSmart(Smart smart) {
			this.smart = smart;
		}

		public static class Smart {

			private Years years;

			public Years getYears() {
				return years;
			}

			public void setYears(Years years) {
				this.years = years;
			}

			public static class Years {
				private double low;
				private int mid;
				private int high;
				private int highPercent;
				private int highMidPercent;
				private int midLowPercent;
				private int lowPercent;

				public double getLow() {
					return low;
				}

				public void setLow(double low) {
					this.low = low;
				}

				public int getMid() {
					return mid;
				}

				public void setMid(int mid) {
					this.mid = mid;
				}

				public int getHigh() {
					return high;
				}

				public void setHigh(int high) {
					this.high = high;
				}

				public int getHighPercent() {
					return highPercent;
				}

				public void setHighPercent(int highPercent) {
					this.highPercent = highPercent;
				}

				public int getHighMidPercent() {
					return highMidPercent;
				}

				public void setHighMidPercent(int highMidPercent) {
					this.highMidPercent = highMidPercent;
				}

				public int getMidLowPercent() {
					return midLowPercent;
				}

				public void setMidLowPercent(int midLowPercent) {
					this.midLowPercent = midLowPercent;
				}

				public int getLowPercent() {
					return lowPercent;
				}

				public void setLowPercent(int lowPercent) {
					this.lowPercent = lowPercent;
				}

			}

		}

	}

}
