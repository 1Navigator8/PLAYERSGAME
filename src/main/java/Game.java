import java.util.*;

public class Game {

    public static void printPlayers(List<Player> players){
        System.out.println("---------------------"+players.get(0).getLeague()+"-----------------------");
        for (Player p:players) {
            System.out.println(String.format("%-15s %-15s %-5d %-5d %-10s",
                    p.getName(), p.getSurname(), p.getAge(), p.getScore(), p.getLeague()));
        }
    }

    public void makeGame(List<Player> players){
        for(int k = 0; k < 5; k++){
            for (int i = 0; i < players.size() - 1; i++){
                Player p1 = players.get(i);
                for(int j = i + 1; j < players.size(); j++){
                    Player p2 = players.get(j);
                    resultGame(p1,p2);
                }
            }
        }
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                int res = o2.getScore() - o1.getScore();
                if(res == 0)
                    res = o1.getName().compareTo(o2.getName());
                return res;
            }
        });
        printPlayers(players);
    }

    public void resultGame(Player p1, Player p2){
        if(Math.random() > 0.5)
            p2.addScore(1);
        else p1.addScore(1);
    }
    public void movePlayersBetweenLeague(Map<League,List<Player>> map){
        Map<League,List<Player>>leaders = new HashMap<>();
        List<Player>leadersList = new ArrayList<>();
        List<Player>losersList = new ArrayList<>();
        for (Map.Entry<League,List<Player>>player : map.entrySet()){
            if (player.getKey().equals(League.JUNIOR))
                for(int i = 0; i<3;i++){
                    PlayerManager.getInstance().changeLeague(map.get(League.JUNIOR).get(i),League.MIDDLE);
                    leadersList.add(map.get(League.JUNIOR).get(i));
                    map.get(League.JUNIOR).get(i).setLeague(League.MIDDLE);
                }
            if (player.getKey().equals(League.SENIOR))
                for(int i = 0; i<3;i++){
                    PlayerManager.getInstance().changeLeague(map.get(League.SENIOR).get(i),League.SENIOR);
                    leadersList.add(map.get(League.MIDDLE).get(i));
                    map.get(League.MIDDLE).get(i).setLeague(League.SENIOR);
                }
        }
        // Iterates via map from  each League take 3  beste players
        // and reassign League in using playerManager function with changeLeague function
    }
}
