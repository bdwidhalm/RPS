package chapter10;

public class Matt implements Player
{
  private Hand lastHand;

  public Matt()
  {
    lastHand = Hand.PAPER;
  }

  @Override
  public String getName()
  {
    return this.getClass().getSimpleName();
  }

  @Override
  public Hand getNextHand()
  {
    // Always pick paper, rock, paper, rock, paper, rock, paper, rock, paper, rock, paper, rock, etc...

    if (Hand.PAPER.equals(lastHand))
    {
      lastHand = Hand.ROCK;
    }
    else if (Hand.ROCK.equals(lastHand))
    {
      lastHand = Hand.PAPER;
    }

    return lastHand;
  }

  @Override
  public void setOpponentsLastHand(Hand hand)
  {
    // TODO Auto-generated method stub
  }

}