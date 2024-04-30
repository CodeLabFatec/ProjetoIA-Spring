package br.gov.sp.fatec.projetoia.responses;

public class PeopleCountResponse {
    private int peopleByRedZone;

    public PeopleCountResponse(int peopleByRedZone) {
        this.peopleByRedZone = peopleByRedZone;
    }

    public int getPeopleByRedZone() {
        return peopleByRedZone;
    }

    public void setPeopleByRedZone(int peopleByRedZone) {
        this.peopleByRedZone = peopleByRedZone;
    }
}
