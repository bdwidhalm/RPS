package chapter10;

public class Main
{
  private static HandComparator c = new HandComparator();

  public static void main(String[] args)
  {
    displayTest();

    Player player1 = new History();
    Player player2 = new Woody();
    int win1 = 0;
    int win2 = 0;
    int numberOfGames = 10000;

    for (int i = 0; i < numberOfGames; i++)
    {
      Hand h1 = player1.getNextHand();
      Hand h2 = player2.getNextHand();
      System.out.println(player1.getName() + " plays: " + h1 + "   " + player2.getName() + " plays: " + h2);

      if (c.compare(h1, h2) > 0)
      {
        win1++;
      }
      else if (c.compare(h1, h2) < 0)
      {
        win2++;
      }

      player1.setOpponentsLastHand(h2);
      player2.setOpponentsLastHand(h1);
    }

    System.out.println(player1.getName() + " won " + win1 + " out of " + numberOfGames + " games.");
    System.out.println(player2.getName() + " won " + win2 + " out of " + numberOfGames + " games.");
  }


  private static void displayTest()
  {
    System.out.println("------------------------------------------------");

    System.out.println("Paper vs. Paper = " + c.compare(Hand.PAPER, Hand.PAPER));
    System.out.println("Paper vs. Rock = " + c.compare(Hand.PAPER, Hand.ROCK));
    System.out.println("Paper vs. Scissors = " + c.compare(Hand.PAPER, Hand.SCISSORS));

    System.out.println("Rock vs. Rock = " + c.compare(Hand.ROCK, Hand.ROCK));
    System.out.println("Rock vs. Paper = " + c.compare(Hand.ROCK, Hand.PAPER));
    System.out.println("Rock vs. Scissors = " + c.compare(Hand.ROCK, Hand.SCISSORS));

    System.out.println("Scissors vs. Scissors = " + c.compare(Hand.SCISSORS, Hand.SCISSORS));
    System.out.println("Scissors vs. Rock = " + c.compare(Hand.SCISSORS, Hand.ROCK));
    System.out.println("Scissors vs. Paper = " + c.compare(Hand.SCISSORS, Hand.PAPER));

    System.out.println("------------------------------------------------\n\n");
  }

}