package Carinfo;

public class MtchCN {
    private String idCN;
    private String dtGMTCN;
    private String mtchTypeCN;
    private String statusCN;
    private String msCN;
    private String t1CN;
    private String t2CN;
    private String t1sCN;
    private String t2sCN;
    private String t1imgCN;
    private String t2imgCN;

    public MtchCN(String idCN, String dtGMTCN, String mtchTypeCN, String statusCN, String msCN, String t1CN, String t2CN, String t1sCN, String t2sCN, String t1imgCN, String t2imgCN) {
        this.idCN = idCN;
        this.dtGMTCN = dtGMTCN;
        this.mtchTypeCN = mtchTypeCN;
        this.statusCN = statusCN;
        this.msCN = msCN;
        this.t1CN = t1CN;
        this.t2CN = t2CN;
        this.t1sCN = t1sCN;
        this.t2sCN = t2sCN;
        this.t1imgCN = t1imgCN;
        this.t2imgCN = t2imgCN;
    }

    public int getTeam1ScoreCN() {
        return parseScoreCN(this.t1sCN);
    }

    public int getTeam2ScoreCN() {
        return parseScoreCN(this.t2sCN);
    }

    public String getHghestScoringTeamCN() {
        return getTeam1ScoreCN() > getTeam2ScoreCN() ? this.t1CN : this.t2CN;
    }

    public int getHghestScoreCN() {
        return Math.max(getTeam1ScoreCN(), getTeam2ScoreCN());
    }

    private int parseScoreCN(String scoreCN) {
        if (scoreCN == null || scoreCN.isEmpty() || scoreCN.equals("0/0")) {
            return 0;
        }

        String[] partsCN = scoreCN.split("/");
        return Integer.parseInt(partsCN[0]);
    }
}
