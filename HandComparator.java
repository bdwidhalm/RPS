package chapter10;

import java.util.Comparator;

class HandComparator implements Comparator<Hand>
{
  // old comparator
  // public int compare(Hand o1, Hand o2)
  // {
  //   if (Hand.ROCK.equals(o1) && Hand.SCISSORS.equals(o2))
  //   {
  //     return 1;
  //   }
  //   else if (Hand.SCISSORS.equals(o1) && Hand.ROCK.equals(o2))
  //   {
  //     return -1;
  //   }
  //
  // If the hands are something other than rock and scissors, the case that wraps, then the regular compare works because of the order the enum is in.
  //   return o1.compareTo(o2);
  // }
  
  // new comparator:
  public int compare(Hand o1, Hand o2)
  {
    if (Hand.ROCK.equals(o1) && Hand.SCISSORS.equals(o2))
    {
      return 1;
    }
    else if (Hand.SCISSORS.equals(o1) && Hand.ROCK.equals(o2))
    {
      return -1;
    }
    else if (o1.compareTo(o2) > 0)
    {
      return 1;
    }
    else if (o1.compareTo(o2) < 0)
    {
      return -1;
    }

    return 0;
  }
}
