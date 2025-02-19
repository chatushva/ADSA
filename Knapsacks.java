import java.util.Arrays;

class Item {
    int weight, profit;
    double ratio;
    
    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.ratio = (double) profit / weight;
    }
}

public class Knapsacks {
    
    public static double getMaxProfit(Item[] items, int m) {
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));
        
        double maxProfit = 0.0;
        System.out.println("Items are placed inside the bag:");
        
        for (Item item : items) {
            if (item.weight <= m) {
                maxProfit += item.profit;
                System.out.println("Item (weight: " + item.weight + ", profit: " + item.profit + ") - taken fully");
                m -= item.weight;  
            } else {
                double fraction = (double) m / item.weight;
                maxProfit += item.profit * fraction;
                System.out.println("Item (weight: " + (item.weight * fraction) + ", profit: " + (item.profit * fraction) + ") - taken " + (fraction * 100) + "%");
                break;  
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        Item[] items = { 
            new Item(10, 60), 
            new Item(20, 100), 
            new Item(30, 120) 
        };
        int m = 50; 
        double maxProfit = getMaxProfit(items, m);
        System.out.println("Max profit in knapsack: " + maxProfit);
    }
}
