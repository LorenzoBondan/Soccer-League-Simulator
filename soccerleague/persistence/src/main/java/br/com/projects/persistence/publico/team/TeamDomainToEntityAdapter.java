package br.com.projects.persistence.publico.team;

import br.com.projects.domain.Convertable;
import br.com.projects.domain.business.publico.championshipteam.DChampionshipTeam;
import br.com.projects.domain.business.publico.player.DPlayer;
import br.com.projects.domain.business.publico.team.DTeam;
import br.com.projects.domain.business.publico.trophyteam.DTrophyTeam;
import br.com.projects.persistence.entities.Stadium;
import br.com.projects.persistence.entities.Team;
import br.com.projects.persistence.entities.TeamAttachment;
import br.com.projects.persistence.publico.championshipteam.ChampionshipTeamDomainToEntityAdapter;
import br.com.projects.persistence.publico.stadium.StadiumDomainToEntityAdapter;
import br.com.projects.persistence.publico.teamattachment.TeamAttachmentDomainToEntityAdapter;
import br.com.projects.persistence.publico.trophyteam.TrophyTeamDomainToEntityAdapter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TeamDomainToEntityAdapter implements Convertable<Team, DTeam> {

    private final ChampionshipTeamDomainToEntityAdapter championshipTeamDomainToEntityAdapter;
    private final StadiumDomainToEntityAdapter stadiumDomainToEntityAdapter;
    private final TrophyTeamDomainToEntityAdapter trophyTeamDomainToEntityAdapter;
    private final TeamAttachmentDomainToEntityAdapter teamAttachmentDomainToEntityAdapter;

    public TeamDomainToEntityAdapter(ChampionshipTeamDomainToEntityAdapter championshipTeamDomainToEntityAdapter, StadiumDomainToEntityAdapter stadiumDomainToEntityAdapter, TrophyTeamDomainToEntityAdapter trophyTeamDomainToEntityAdapter, TeamAttachmentDomainToEntityAdapter teamAttachmentDomainToEntityAdapter) {
        this.championshipTeamDomainToEntityAdapter = championshipTeamDomainToEntityAdapter;
        this.stadiumDomainToEntityAdapter = stadiumDomainToEntityAdapter;
        this.trophyTeamDomainToEntityAdapter = trophyTeamDomainToEntityAdapter;
        this.teamAttachmentDomainToEntityAdapter = teamAttachmentDomainToEntityAdapter;
    }

    @Override
    public Team toEntity(DTeam domain) {
        return Team.builder()
                .id(domain.getId())
                .name(domain.getName())
                .members(domain.getMembers())
                .stadium(new Stadium(domain.getStadium().getId()))
                .teamAttachment(Optional.ofNullable(domain.getTeamAttachment())
                        .map(TeamAttachment -> new TeamAttachment(TeamAttachment.getId()))
                        .orElse(null))
                .build();
    }

    @Override
    public DTeam toDomain(Team entity) {

        List<DChampionshipTeam> championshipTeams = Optional.ofNullable(entity.getChampionshipsTeam())
                .map(lista -> lista.stream()
                        .map(championshipTeamDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        List<DPlayer> players = Optional.ofNullable(entity.getPlayers())
                .map(lista -> lista.stream()
                        .map(player -> new DPlayer(player.getId()))
                        .toList())
                .orElse(List.of());

        List<DTrophyTeam> trophiesTeam = Optional.ofNullable(entity.getTrophyTeams())
                .map(lista -> lista.stream()
                        .map(trophyTeamDomainToEntityAdapter::toDomain)
                        .toList())
                .orElse(List.of());

        return DTeam.builder()
                .id(entity.getId())
                .name(entity.getName())
                .members(entity.getMembers())
                .stadium(Optional.ofNullable(entity.getStadium())
                        .map(stadiumDomainToEntityAdapter::toDomain)
                        .orElse(null))
                .teamAttachment(Optional.ofNullable(entity.getTeamAttachment())
                        .map(teamAttachmentDomainToEntityAdapter::toDomain)
                        .orElse(null))

                .championshipsTeam(championshipTeams)
                .players(players)
                .trophiesTeam(trophiesTeam)

                .build();
    }
}
