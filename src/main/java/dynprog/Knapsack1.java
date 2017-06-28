/**
 * Created by marco on 15/03/14.
 */
 
package dynprog;

import java.util.LinkedList;
 
public class Knapsack1 {
 
    private int[][] table; // table used for dynamic programming.
    private String[] items; // Name of each item.
    private int[] weight; // weight of each individual item.
    private int[] value; // value of each individual item.
    private int limit; // limit of the bag
    private LinkedList<String> result;
 
    public Knapsack1(String[] items, int[] weight, int[] value, int limit) throws Exception
    {
        int item_length = items.length;
        int weight_length = weight.length;
        int value_length = value.length;
 
        if(item_length!=weight_length || item_length!=value_length || weight_length!=value_length)
        {
            throw new Exception("Missing information");
        }
        this.items = items;
        this.weight = weight;
        this.value = value;
        this.limit = limit;
    }
 
    public void solve_dynamic(String[] items, int[] weight, int[] value, int limit)
    {
        int first_term;
        int second_term;
        result = new LinkedList<String>();
        table = new int[items.length+1][limit+1]; // construct the table
        for(int i = 1; i < items.length+1; i++)
        {
            for(int j = 1; j < limit+1; j++)
            {
                int w = j - weight[i-1];
                if(w<0)
                {
                    first_term = 0;
                }
                else{
                    first_term = table[i-1][w]+value[i-1];
                }
                second_term = table[i-1][j];
                table[i][j] = Math.max(first_term,second_term);
            }
        }
 
        // Select the relevant items
        int i = items.length;
        int l = limit;
        while(l >0)
        {
            if(table[i][l]==table[i-1][l])
            {
                i = i-1;
                continue;
            }
            else
            {
                result.add(items[i-1]);
                i=i-1;
                l = l-weight[i];
            }
        }
    }
 
 
 
    public static void main(String[] args)
    {
        String[] s = {"one","two","three"};
        int[] w = {1,2,3};
        int[] v = {2,3,4};
        int l = 5;
        try{
        Knapsack1 k = new Knapsack1(s,w,v,l);
            k.solve_dynamic(s,w,v,l);
        } catch (Exception e){
 
        }
    }
}