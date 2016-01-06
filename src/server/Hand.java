package server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand
{
	private List<Card> playerHand;
	private List<Card> tableCards;
	public int[] handValue;
	
	public void evaluateHand()
	{
		final int[][] combinations = {{1,2,3}, {1,2,4}, {1,3,4}, {2,3,4}, {1,2,5}, {1,3,5}, {1,4,5}, {2,3,5}, {2,4,5}, {3,4,5}};
		

		int nk;
		int[][] allCombinations;
		
		if(tableCards.size() == 3) nk = 1;
		else if(tableCards.size() == 4) nk = 4;
		else nk = 10;
		
		allCombinations = new int[nk][];
		
		for(int i = 0; i < nk; i++)
		{
			List<Card> bestHand = new ArrayList<Card>();
			for(int j = 0; j < 3; j++)
			{
				bestHand.add(tableCards.get(combinations[i][j] - 1));
			}
			bestHand.addAll(playerHand);
			bestHand.sort((c1, c2) -> c2.getValue() - c1.getValue());
			
			allCombinations[i] = evaluate(bestHand);
		}
		
		/*for(int z = 0; z < nk; z++)
		{
			for(int x = 0; x < 6; x++)	System.out.print(allCombinations[z][x] + ", ");
			System.out.println();
		}*/
		
		int[] maxCombination = allCombinations[0];
		
		for(int i = 1; i < allCombinations.length; i++)
		{
			for(int j = 0; j < 6; j++)
			{
				if(allCombinations[i][j] > maxCombination[j])
				{
					maxCombination = allCombinations[i];
				}
				else if(allCombinations[i][j] < maxCombination[j]) break;
			}
		}
		
		handValue = maxCombination;
		
	}
	
	private int[] evaluate(List<Card> hand)
	{
		int[] ret = {0, 0, 0, 0, 0, 0};
		
		//Poker
		ret[0] = 8;
		for(int i = 0; i < 5; i++)
		{
			if(i == 4)
			{
				for(int j = 1; j < 6; j++)
				{
					ret[j] = hand.get(j-1).getValue();
				}
				return ret;
			}
			if(hand.get(i).getValue() != hand.get(i+1).getValue() + 1 || hand.get(i).getColor() != hand.get(i+1).getColor())
			{
				for(int j = 0; j < 6; j++)
				{
					ret[j] = 0;
				}
				break;
			}
		}
		
		//Four
		ret[0] = 7;
		
		if(hand.get(0).getValue() == hand.get(3).getValue() ||
		   hand.get(1).getValue() == hand.get(4).getValue())
		{
			ret[1] =  hand.get(2).getValue();
			return ret;
		}
		
		//Full
		ret[0] = 6;
		
		if((hand.get(0).getValue() == hand.get(2).getValue() &&
		   hand.get(3).getValue() == hand.get(4).getValue()) ||
		   (hand.get(0).getValue() == hand.get(1).getValue() &&
		   hand.get(2).getValue() == hand.get(4).getValue()))
		{
			ret[1] = hand.get(0).getValue();
			ret[2] = hand.get(4).getValue();
			return ret;
		}
		
		//Flush
		ret[0] = 5;
		
		for(int i = 0; i < 5; i++)
		{
			if(i == 4)
			{
				for(int j = 1; j < 6; j++)
				{
					ret[j] = hand.get(j-1).getValue();
				}
				return ret;
			}
			if(hand.get(i).getColor() != hand.get(i+1).getColor())
			{
				for(int j = 0; j < 6; j++)
				{
					ret[j] = 0;
				}
				break;
			}
		}
		
		//Straight
		ret[0] = 4;
		
		for(int i = 0; i < 5; i++)
		{
			if(i == 4)
			{
				for(int j = 1; j < 6; j++)
				{
					ret[j] = hand.get(j-1).getValue();
				}
				return ret;
			}
			if(hand.get(i).getValue() != hand.get(i+1).getValue() + 1)
			{
				for(int j = 0; j < 6; j++)
				{
					ret[j] = 0;
				}
				break;
			}
		}
		
		//Three
		ret[0] = 3;
		
		if(hand.get(0).getValue() == hand.get(2).getValue() ||
		   hand.get(1).getValue() == hand.get(3).getValue() ||
		   hand.get(2).getValue() == hand.get(4).getValue())
		{
			ret[1] = hand.get(2).getValue();
			return ret;
		}
		
		//Two Pair
		ret[0] = 2;
		
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
		   hand.get(2).getValue() == hand.get(3).getValue())
		{
			ret[1] = hand.get(0).getValue();
			ret[2] = hand.get(2).getValue();
			ret[3] = hand.get(4).getValue();
			return ret;
		}
		if(hand.get(1).getValue() == hand.get(2).getValue() &&
		   hand.get(3).getValue() == hand.get(4).getValue())
		{
			ret[1] = hand.get(1).getValue();
			ret[2] = hand.get(3).getValue();
			ret[3] = hand.get(0).getValue();
			return ret;
		}
		if(hand.get(0).getValue() == hand.get(1).getValue() &&
		   hand.get(3).getValue() == hand.get(4).getValue())
		{
			ret[1] = hand.get(0).getValue();
			ret[2] = hand.get(3).getValue();
			ret[3] = hand.get(2).getValue();
			return ret;
		}
		/*int i,j;
		
		for(i = 0; i < 2; i++)
		{
			if(hand.get(i).getValue() == hand.get(i+1).getValue())
			{
				for(j = i + 2; j < i + 4; j++)
				{
					if(hand.get(j).getValue() == hand.get(j+1).getValue())
					{
						ret[1] = hand.get(i).getValue();
						ret[2] = hand.get(j).getValue();
						int otherCard = (i == 0) ? ((j == 2) ? 4 : 2) : 0;
						ret[3] = hand.get(otherCard).getValue();
						return ret;
					}
				}
			}
		}*/
		
		//Pair
		ret[0] = 1;
		for(int i = 0; i < 4; i++)
		{
			if(hand.get(i).getValue() == hand.get(i+1).getValue())
			{
				ret[1] = hand.get(i).getValue();
				for(int j = (i==0)?2:0, k = 2; j < 5; j++)
				{
					if(j==i || j==i+1)
					{
						j++;
						continue;
					}
					ret[k++] = hand.get(j).getValue();
				}
				return ret;
			}
		}
		
		ret[0] = 0;
		
		for(int i = 0; i < 5; i++) ret[i+1] = hand.get(i).getValue();
		
		return ret;
	}
	
	public void addToHand(Card card)
	{
		playerHand.add(card);
	}
	
	public int compareTo(Hand handToCompare)
	{
		for(int i = 0; i < 5; i++)
		{
			if(handValue[i] < handToCompare.handValue[i]) return -1;
			else if(handValue[i] > handToCompare.handValue[i]) return 1;
		}
		return 0;
	}
	
	public Hand(List<Card> tableCards)
	{
		playerHand = new ArrayList<Card>();
		this.tableCards = tableCards;
	}
}
