package chapter10;

public class History implements Player
{
  private Hand lastHand;
  private String oppHist = "";
  private String nextGuess = "";

  public History()
  {
    lastHand = Hand.PAPER; // play paper first
  }

  @Override
  public String getName()
  {
    return this.getClass().getSimpleName();
  }

  @Override
  public Hand getNextHand()
  {
    // History Matching
    nextGuess = matchHistory();

    if (nextGuess.equals("R"))
    {
      lastHand = Hand.PAPER;
    }
    else if (nextGuess.equals("P"))
    {
      lastHand = Hand.SCISSORS;
    }
    else if (nextGuess.equals("S"))
    {
      lastHand = Hand.ROCK;
    }

    return lastHand;
  }

  @Override
  public void setOpponentsLastHand(Hand hand)
  {
    // TODO Auto-generated method stub
    if (Hand.ROCK.equals(hand))
    {
      oppHist = oppHist + "R";
    }
    else if (Hand.PAPER.equals(hand))
    {
      oppHist = oppHist + "P";
    }
    else if (Hand.SCISSORS.equals(hand))
    {
      oppHist = oppHist + "S";
    }
  }
  
  private String matchHistory()
  {
    String currentPattern = "";
    String checkHistory = "";
    String nextPlay = "";
    
    if (oppHist.length() > 5 && oppHist.length() <= 11)
    {
      currentPattern = oppHist.substring(oppHist.length() - 3);
      checkHistory = oppHist.substring(0, oppHist.length() - 4);
      if (checkHistory.contains(currentPattern))
      {
        nextPlay = oppHist.charAt(checkHistory.indexOf(currentPattern) + 3) + "";
      }
    }
    else if (oppHist.length() > 11)
    {
      currentPattern = oppHist.substring(oppHist.length() - 5);
      checkHistory = oppHist.substring(0, oppHist.length() - 6);
      if (checkHistory.contains(currentPattern))
      {
        nextPlay = oppHist.charAt(checkHistory.indexOf(currentPattern) + 5) + "";
      }
    }
    
    return nextPlay;
  }
}
