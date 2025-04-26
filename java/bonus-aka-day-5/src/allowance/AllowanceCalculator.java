package allowance;

import java.util.Collections;
import java.util.List;

public class AllowanceCalculator {
    private List<IAllowance> allowances;


    public AllowanceCalculator(List<IAllowance> allowances) {
        this.allowances = allowances;
    }

    public double calculateTotalAllowances() {
        double totalAllowances = 0;
        for (IAllowance allowance : allowances) {
            totalAllowances += allowance.calculateTotalAllowance();
        }
        return totalAllowances;
    }

    public List<IAllowance> getAllowances() {
        return Collections.unmodifiableList(allowances);
    }
}
