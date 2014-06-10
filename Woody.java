package chapter10;

public class Woody implements Player
{
  private Hand lastHand; // last hand played by opponent
  private Hand thisHand; // current hand played
  // arrays to count what's played
  private int[] afterRock = { 0, 0, 0 };
  private int[] afterPaper = { 0, 0, 0 };
  private int[] afterScissors = { 0, 0, 0 };
  private String oppHist = "";
  private String myHist = "";

  public Woody()
  {
    thisHand = Hand.PAPER; // play paper first
  }

  @Override
  public String getName()
  {
    return this.getClass().getSimpleName();
  }

  @Override
  public Hand getNextHand()
  {
    // guess at what opponent will play next
    String guess = "";
    String markovGuess = markovBased();
    String noviceGuess = noviceGuess();
    String novice2Guess = novice2Guess();
    
    if (!novice2Guess.equals(" "))
    {
      guess = novice2Guess;
    }
    if (novice2Guess.equals(" ") && !noviceGuess.equals(" "))
    {
      guess = noviceGuess;
    }
    if (novice2Guess.equals(" ") && noviceGuess.equals(" "))
    {
      guess = markovGuess;
    }

    if (guess.equals("R"))
    { // play paper to beat rock
      thisHand = Hand.PAPER;
      myHist = myHist + "P";
    }
    else if (guess.equals("P"))
    { // play scissors to beat paper
      thisHand = Hand.SCISSORS;
      myHist = myHist + "S";
    }
    else if (guess.equals("S"))
    { // play rock to beat scissors
      thisHand = Hand.ROCK;
      myHist = myHist + "R";
    }


    // return what we want to play
    return thisHand;
  }

  @Override
  public void setOpponentsLastHand(Hand hand)
  {
    // increment counters to guess at what will be played next
    if (Hand.ROCK.equals(lastHand) && Hand.ROCK.equals(hand))
    {
      afterRock[0]++;
    }
    else if (Hand.ROCK.equals(lastHand) && Hand.PAPER.equals(hand))
    {
      afterRock[1]++;
    }
    else if (Hand.ROCK.equals(lastHand) && Hand.SCISSORS.equals(hand))
    {
      afterRock[2]++;
    }
    else if (Hand.PAPER.equals(lastHand) && Hand.ROCK.equals(hand))
    {
      afterPaper[0]++;
    }
    else if (Hand.PAPER.equals(lastHand) && Hand.PAPER.equals(hand))
    {
      afterPaper[1]++;
    }
    else if (Hand.PAPER.equals(lastHand) && Hand.SCISSORS.equals(hand))
    {
      afterPaper[2]++;
    }
    else if (Hand.SCISSORS.equals(lastHand) && Hand.ROCK.equals(hand))
    {
      afterScissors[0]++;
    }
    else if (Hand.SCISSORS.equals(lastHand) && Hand.PAPER.equals(hand))
    {
      afterScissors[1]++;
    }
    else if (Hand.SCISSORS.equals(lastHand) && Hand.SCISSORS.equals(hand))
    {
      afterScissors[2]++;
    }
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

    lastHand = hand;
  }

  private String markovBased()
  {
    // array index to determine rock, paper, or scissors
    int index = 1;
    // variable to find max count
    int maximum = -1;
    // string for the guess
    String markovGuess = "";

    if (Hand.ROCK.equals(lastHand))
    { // after rock is played
      for (int i = 0; i < afterRock.length; i++)
      {
        if (afterRock[i] > maximum)
        { // find what's played most
          maximum = afterRock[i];
          index = i;
        }
      }
    }
    else if (Hand.PAPER.equals(lastHand))
    {
      for (int i = 0; i < afterPaper.length; i++)
      {
        if (afterPaper[i] > maximum)
        { // find what's played most
          maximum = afterPaper[i];
          index = i;
        }
      }
    }
    else if (Hand.SCISSORS.equals(lastHand))
    {
      for (int i = 0; i < afterScissors.length; i++)
      {
        if (afterScissors[i] > maximum)
        { // find what's played most
          maximum = afterScissors[i];
          index = i;
        }
      }
    }

    if (index == 0)
    { // rock
      markovGuess = "R";
    }
    else if (index == 1)
    { // paper
      markovGuess = "P";
    }
    else if (index == 2)
    { // scissors
      markovGuess = "S";
    }


    // return the guess
    return markovGuess;
  }

  private String noviceGuess()
  {
    String currentPattern = "";
    String checkHistory = "";
    String nextPlay = "";
    int rockOccur = 0;
    int paperOccur = 0;
    int scissorOccur = 0;
    String nextGuess = "";

    if (oppHist.length() == 0)
    {
      return "R";
    }
    else if (oppHist.length() == 1)
    {
      return "R";
    }
    else if (oppHist.length() == 2)
    {
      return "P";
    }
    else if (oppHist.length() == 3)
    {
      return "R";
    }
    else if (oppHist.length() == 4)
    {
      return "P";
    }
    else if (oppHist.length() == 5)
    {
      return "S";
    }
    else if (oppHist.length() > 5)
    {
      currentPattern = oppHist.substring(oppHist.length() - 4);
      checkHistory = oppHist.substring(0, oppHist.length() - 4);
      while (checkHistory.contains(currentPattern) && checkHistory.length() > (checkHistory.indexOf(currentPattern) + 4))
      {

        nextPlay = checkHistory.charAt(checkHistory.indexOf(currentPattern) + 4) + "";

        checkHistory = checkHistory.substring(checkHistory.indexOf(currentPattern) + 4);

        if (nextPlay.equals("R"))
        {
          rockOccur++;
        }
        else if (nextPlay.equals("P"))
        {
          paperOccur++;
        }
        else if (nextPlay.equals("S"))
        {
          scissorOccur++;
        }
      }
    }
    if (rockOccur > paperOccur && rockOccur > scissorOccur)
    {
      nextGuess = "R";
    }
    else if (paperOccur > rockOccur && paperOccur > scissorOccur)
    {
      nextGuess = "P";
    }
    else if (scissorOccur > rockOccur && scissorOccur > paperOccur)
    {
      nextGuess = "S";
    }

    if (nextGuess.equals(""))
    {
      nextGuess = " ";
    }

    return nextGuess;
  }

  private String novice2Guess()
  {
    String currentPattern = "";
    String myCurrentPattern = "";
    String checkHistory = "";
    String myCheckHistory = "";
    String nextPlay = "";
    int rockOccur = 0;
    int paperOccur = 0;
    int scissorOccur = 0;
    String nextGuess = "";

    if (oppHist.length() == 0)
    {
      return "R";
    }
    else if (oppHist.length() == 1)
    {
      return "R";
    }
    else if (oppHist.length() == 2)
    {
      return "P";
    }
    else if (oppHist.length() == 3)
    {
      return "R";
    }
    else if (oppHist.length() == 4)
    {
      return "P";
    }
    else if (oppHist.length() == 5)
    {
      return "S";
    }
    else if (oppHist.length() > 5)
    {
      currentPattern = oppHist.substring(oppHist.length() - 4);
      myCurrentPattern = myHist.substring(myHist.length() - 4);
      checkHistory = oppHist.substring(0, oppHist.length() - 4);
      myCheckHistory = myHist.substring(0, myHist.length() - 4);

      while (checkHistory.contains(currentPattern) && checkHistory.length() > (checkHistory.indexOf(currentPattern) + 4))
      {

        nextPlay = checkHistory.charAt(checkHistory.indexOf(currentPattern) + 4) + "";
        String checkMyPattern = myCheckHistory.substring(checkHistory.indexOf(currentPattern), checkHistory.indexOf(currentPattern) + 4);
        myCheckHistory = myCheckHistory.substring(checkHistory.indexOf(currentPattern) + 4);
        checkHistory = checkHistory.substring(checkHistory.indexOf(currentPattern) + 4);

        if (checkMyPattern.equals(myCurrentPattern))
        {
          if (nextPlay.equals("R"))
          {
            rockOccur++;
          }
          else if (nextPlay.equals("P"))
          {
            paperOccur++;
          }
          else if (nextPlay.equals("S"))
          {
            scissorOccur++;
          }
        }
      }
    }

    if (rockOccur > paperOccur && rockOccur > scissorOccur)
    {
      nextGuess = "R";
    }
    else if (paperOccur > rockOccur && paperOccur > scissorOccur)
    {
      nextGuess = "P";
    }
    else if (scissorOccur > rockOccur && scissorOccur > paperOccur)
    {
      nextGuess = "S";
    }

    if (nextGuess.equals(""))
    {
      nextGuess = " ";
    }

    return nextGuess;
  }


}
