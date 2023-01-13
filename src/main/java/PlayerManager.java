import java.util.*;

public class PlayerManager {
    private List<Player> players;
    private Map<League, List<Player>> mapOfPlayers;
    private Set<UUID> uuids;
    static PlayerManager pm = null;

    private PlayerManager() {
        mapOfPlayers = new HashMap<>();
        uuids = new HashSet<>();
        for (League l : League.values()) {
            mapOfPlayers.put(l, new ArrayList<>());
        }
    }

    public static PlayerManager getInstance() {
        if (pm == null)
            pm = new PlayerManager();
        return pm;
    }

    //change methode, player must add as key/value
    public boolean addIntoListPlayer(Player player) {
        Boolean res = false;
        if (!uuids.contains(player.getId())) {
            League league = assignLeague(player);
            if (league != League.UNDEFINED) {
                List<Player> players = mapOfPlayers.get(league);
                players.add(player);
                uuids.add(player.getId());
                res = true;
            }
        }
        return res;
    }

    public League assignLeague(Player p) {
        League res = League.UNDEFINED;
        if (p.getAge() >= 15 && p.getAge() <= 17)
            p.setLeague(League.JUNIOR);
        else if (p.getAge() >= 18 && p.getAge() <= 25)
            p.setLeague(League.MIDDLE);
        else if (p.getAge() >= 26 && p.getAge() <= 40)
            p.setLeague(League.SENIOR);
        //else res =League.UNDEFINED ;
        return res;
    }

    public List<Player> getPlayersByLeague(League league) {
//        List<Player>players = mapOfPlayers.get(league);
//        List<Player> playersListByLeague = new LinkedList<>();
        return new LinkedList<>(mapOfPlayers.get(league));
    }

    public boolean changeLeague(Player player, League newleague) {
        boolean res = false;
        if (!player.getLeague().equals(newleague)){
       List<Player> playerOldLeague = mapOfPlayers.get(player.getLeague());
       List<Player> playerNewLeague = mapOfPlayers.get(newleague);
       Iterator<Player> it = playerOldLeague.iterator();
      while (it.hasNext() && !res){
          Player p = it.next();
          if (p.getId().equals(player.getId())){
              it.remove();
              playerNewLeague.add(player);
              res = true;
          }

          }
      }
        return res;

    }
}
