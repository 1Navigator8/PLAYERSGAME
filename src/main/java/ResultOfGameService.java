import java.util.*;

public class ResultOfGameService {
    public static void showTopPlayerIntoLeague(List<Player> players){
        List<Player> bestTopThree = new ArrayList<>();
        bestTopThree.add(players.get(0));
        Game.printPlayers(bestTopThree);
    }

    private static List<Player> getWorstPlayers(List<Player> players){
        List<Player> worstPlayers = new LinkedList<>();
        ListIterator<Player> it = players.listIterator(players.size());
        for(int i = players.size() - 1; i > players.size() - 4; i--) {
            worstPlayers.add(it.previous());
        }

        int last = players.size() - 1;
        if(players.get(last).getLeague() != League.JUNIOR)
            players.remove(last);
        if(players.get(last - 1).getLeague() != League.JUNIOR)
            players.remove(last - 1);
        if(players.get(last - 2).getLeague() != League.JUNIOR)
            players.remove(last - 2);

        return worstPlayers;
    }

    private static List<Player> getBestePlayers(List<Player> players){
        List<Player> bestePlayers = new LinkedList<>();
        ListIterator<Player> it = players.listIterator();
        for(int i = 0; i < 3; i++) {
            bestePlayers.add(it.next());
        }

        int n = 0;
        while(n < 3) {
            if (players.get(0).getLeague() != League.SENIOR)
                players.remove(0);
            n++;
        }
        return bestePlayers;
    }

    public static void movePlayers(List<Player> playersSenior, List<Player> playersMiddle, List<Player> playersJunior){
        List<Player> wPlayersS = new LinkedList<>();
        List<Player> wPlayersM = new LinkedList<>();
        List<Player> wPlayersJ = new LinkedList<>();
        if(playersSenior.get(0).getLeague() == League.SENIOR)
            wPlayersS = getWorstPlayers(playersSenior);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");
        if(playersMiddle.get(0).getLeague() == League.MIDDLE)
            wPlayersM = getWorstPlayers(playersMiddle);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");
        if(playersJunior.get(0).getLeague() == League.JUNIOR)
            wPlayersJ = getWorstPlayers(playersJunior);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");

        System.out.println("\n------------------WORST PLAYERS-------------------");
        Game.printPlayers(wPlayersS);
        Game.printPlayers(wPlayersM);
        Game.printPlayers(wPlayersJ);

        if(wPlayersS.size() != 0) {
            for (Player wpS : wPlayersS) {
                wpS.setLeague(League.MIDDLE);
                playersMiddle.add(wpS);
            }
        }
        if(wPlayersM.size() != 0) {
            for (Player wpM : wPlayersM) {
                wpM.setLeague(League.JUNIOR);
                playersJunior.add(wpM);
            }
        }

        List<Player> bPlayersS = new LinkedList<>();
        List<Player> bPlayersM = new LinkedList<>();
        List<Player> bPlayersJ = new LinkedList<>();
        if(playersSenior.get(0).getLeague() == League.SENIOR)
            bPlayersS = getBestePlayers(playersSenior);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");
        if(playersMiddle.get(0).getLeague() == League.MIDDLE)
            bPlayersM = getBestePlayers(playersMiddle);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");
        if(playersJunior.get(0).getLeague() == League.JUNIOR)
            bPlayersJ = getBestePlayers(playersJunior);
        else System.out.println("Check list!!! The parameters passed are incorrect in function 'movePlayers'!!!");

        System.out.println("\n------------------BESTE PLAYERS-------------------");
        Game.printPlayers(bPlayersS);
        Game.printPlayers(bPlayersM);
        Game.printPlayers(bPlayersJ);
        System.out.println("--------------------------------------------------\n");

        if(bPlayersM.size() != 0){
            for (Player bPM : bPlayersM) {
                bPM.setLeague(League.SENIOR);
                playersSenior.add(bPM);
            }
        }
        if(bPlayersJ.size() != 0){
            for(Player bPJ : bPlayersJ){
                bPJ.setLeague(League.MIDDLE);
                playersMiddle.add(bPJ);
            }
        }
    }
}
