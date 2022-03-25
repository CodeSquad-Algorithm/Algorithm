class Solution {
    private final static char PEOPLE = 'P';
    private final static char PARTITION = 'X';

    private final static int[] x_move = {0,0,1,-1};
    private final static int[] y_move = {1,-1,0,0};

    private final static int[] x_cross_move = {-1,1,-1,1};
    private final static int[] y_cross_move = {-1,-1,1,1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            boolean isPlaceSafe = isPlaceSafe(place);
            answer[i] = (isPlaceSafe) ? 1 : 0;
        }
        
        return answer;
    }

    private boolean isPlaceSafe(String[] place) {
        char[][] placeMap = getPlaceMap(place);

        for (int y = 0; y < placeMap.length; y++) {
            for (int x = 0; x < placeMap[0].length; x++) {
                char chr = placeMap[y][x];
                if (chr != PEOPLE) continue;
                
                if (!isPositionSafe(placeMap, x, y)) return false;
            }
        }
        
        return true;
    }

    private boolean isPositionSafe(char[][] placeMap, int x, int y) {
        
        // 상하좌우 파티션 한칸 확인
        for (int i = 0; i < x_move.length; i++) {
            int nx = x + x_move[i];
            int ny = y + y_move[i];
            
            if (ny < 0 || ny >= placeMap.length || nx < 0 || nx >= placeMap[0].length) continue;
            
            if (placeMap[ny][nx] == PEOPLE) return false;
        }

        // 상하좌우 맨해튼 거리 2 확인
        for (int i = 0; i < x_move.length; i++) {
            int nx = x + (x_move[i] * 2);
            int ny = y + (y_move[i] * 2);

            if (ny < 0 || ny >= placeMap.length || nx < 0 || nx >= placeMap[0].length) continue;
            
            char distance2 = placeMap[ny][nx];

            nx = x + x_move[i];
            ny = y + y_move[i];

            char center = placeMap[ny][nx];
            
            if (distance2 == PEOPLE && center != PARTITION) return false;
        }
        
        // 대각선 확인
        for (int i = 0; i < x_cross_move.length; i++) {
            int nx = x + x_cross_move[i];
            int ny = y + y_cross_move[i];

            if (ny < 0 || ny >= placeMap.length || nx < 0 || nx >= placeMap[0].length) continue;

            char cross = placeMap[ny][nx];
            char near1 = placeMap[ny][x];
            char near2 = placeMap[y][nx];

            if (cross == PEOPLE && !(near1 == PARTITION && near2 == PARTITION)) return false;
        }
        
        return true;
    }

    private char[][] getPlaceMap(String[] place) {
        char[][] placeMap = new char[place.length][place[0].length()];

        for (int i = 0; i < place.length; i++) {
            placeMap[i] = place[i].toCharArray();
        }
        
        return placeMap;
    }
}