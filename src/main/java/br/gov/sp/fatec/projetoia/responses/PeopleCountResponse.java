package br.gov.sp.fatec.projetoia.responses;

public class PeopleCountResponse {
    private Integer peopleByRedZone;

    public PeopleCountResponse(Integer peopleByRedZone) {
        this.peopleByRedZone = peopleByRedZone;
    }

    public Integer getPeopleByRedZone() {
        return peopleByRedZone;
    }

    public void setPeopleByRedZone(Integer peopleByRedZone) {
        this.peopleByRedZone = peopleByRedZone;
    }
}
