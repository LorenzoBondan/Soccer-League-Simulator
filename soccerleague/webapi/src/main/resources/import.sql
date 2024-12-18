INSERT INTO tb_user (email, name, password) VALUES ('alex@gmail.com', 'Alex', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (email, name, password) VALUES ('maria@gmail.com', 'Maria', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_user (email, name, password) VALUES ('bob@gmail.com', 'Bob', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
--SELECT setval('tb_user_id_seq', 3);

INSERT INTO tb_role (authority) VALUES ( 'ROLE_CLIENT');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');
--SELECT setval('tb_role_id_seq', 2);

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (3, 2);

INSERT INTO tb_country (name) VALUES ('Brazil');

INSERT INTO tb_league (country_id, name) VALUES (1, 'Campeonato Brasileiro');

INSERT INTO tb_season (season_year) VALUES (2024);

INSERT INTO tb_championship (league_id, season_id) VALUES (1, 1);

INSERT INTO tb_stadium (name, capacity, latitude, longitude) VALUES ('Maracanã', 78838, -22.9111, -43.2302);
INSERT INTO tb_stadium (name, capacity, latitude, longitude) VALUES ('Arena Corinthians', 49923, -23.5349, -46.4631);

INSERT INTO tb_team (name, stadium_id, members) VALUES ('Flamengo', 1, 25);
INSERT INTO tb_team (name, stadium_id, members) VALUES ('Corinthians', 2, 25);
INSERT INTO tb_team (name, stadium_id, members) VALUES ('Palmeiras', 1, 25);
INSERT INTO tb_team (name, stadium_id, members) VALUES ('São Paulo', 2, 25);

INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Goalkeeper', 'GK', 0);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Right Back', 'RB', 1);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Left Back', 'LB', 1);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Centre Back', 'CB', 1);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Defensive Midfielder', 'DMF', 2);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Centre Midfielder', 'CMF', 2);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Attacking Midfielder', 'AMF', 2);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Left Midfielder', 'LMF', 2);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Right Midfielder', 'RMF', 2);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Left Wing Forward', 'LWF', 3);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Right Wing Forward', 'RWF', 3);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Second Striker', 'SS', 3);
INSERT INTO tb_position (name, acronym, field_zone) VALUES ('Centre Forward', 'CF', 3);

-- Flamengo
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (1, 'Diego Alves', 'Diego', '1985-06-24');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (1, 'Rodrigo Caio', 'Rodrigo', '1993-10-07');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (1, 'Everton Ribeiro', 'Everton', '1989-03-10');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (1, 'Gabigol', 'Gabriel Barbosa', '1996-08-30');

INSERT INTO tb_player_position VALUES (1,1);
INSERT INTO tb_player_position VALUES (2,4);
INSERT INTO tb_player_position VALUES (3,7);
INSERT INTO tb_player_position VALUES (3,8);
INSERT INTO tb_player_position VALUES (3,9);
INSERT INTO tb_player_position VALUES (4,13);

-- Corinthians
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (2, 'Cássio', 'Cássio', '1987-06-06');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (2, 'Gil', 'Gil', '1986-09-04');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (2, 'Jô', 'Jô', '1987-10-20');
INSERT INTO tb_player (team_id, name, nickname, birth_date) VALUES (2, 'Ramires', 'Ramires', '1987-03-24');

INSERT INTO tb_player_position VALUES (5,1);
INSERT INTO tb_player_position VALUES (6,4);
INSERT INTO tb_player_position VALUES (7,13);
INSERT INTO tb_player_position VALUES (8,5);
INSERT INTO tb_player_position VALUES (8,6);
INSERT INTO tb_player_position VALUES (8,7);

INSERT INTO tb_match_day (championship_id, number) VALUES (1, 1);
INSERT INTO tb_match_day (championship_id, number) VALUES (1, 2);

INSERT INTO tb_match (home_team_id, away_team_id, home_team_goals, away_team_goals, match_day_id, stadium_id, date, attendees) VALUES (1, 2, 2, 1, 1, 1, '2024-08-15 21:00:00', 50000);
INSERT INTO tb_match (home_team_id, away_team_id, home_team_goals, away_team_goals, match_day_id, stadium_id, date, attendees) VALUES (2, 1, 1, 1, 2, 2, '2024-08-20 21:00:00', 40000);

-- Flamengo vs Corinthians (match_id = 1)
INSERT INTO tb_match_event (match_id, minute_match_event, player1_id, description, type) VALUES (1, 15, 4, 'Gabigol scored a goal', 'GOAL');
INSERT INTO tb_match_event (match_id, minute_match_event, player1_id, description, type) VALUES (1, 45, 2, 'Rodrigo Caio received a yellow card', 'YELLOW_CARD');

-- Corinthians vs Flamengo (match_id = 2)
INSERT INTO tb_match_event (match_id, minute_match_event, player1_id, description, type) VALUES (2, 30, 6, 'Cássio made a save', 'GOAL');
INSERT INTO tb_match_event (match_id, minute_match_event, player1_id, description, type) VALUES (2, 70, 7, 'Jô received a red card', 'RED_CARD');

-- Flamengo vs Corinthians (Championship ID = 1)
INSERT INTO tb_player_stats (player_id, championship_id, goals, yellow_cards, red_cards) VALUES (4, 1, 1, 1, 0);  -- Gabigol

INSERT INTO tb_player_stats (player_id, championship_id, goals, yellow_cards, red_cards) VALUES (2, 1, 0, 1, 0);  -- Rodrigo Caio

INSERT INTO tb_placing (championship_id, team_id, points, victories, draws, defeats, goals_scored, goals_conceded, goal_difference) VALUES (1, 1, 3, 1, 0, 0, 2, 1, 1);  -- Flamengo

INSERT INTO tb_placing (championship_id, team_id, points, victories, draws, defeats, goals_scored, goals_conceded, goal_difference) VALUES (1, 2, 0, 0, 0, 1, 1, 2, -1);  -- Corinthians

INSERT INTO tb_trophy (name) VALUES ('Campeonato Brasileiro');
INSERT INTO tb_trophy (name) VALUES ('Copa do Brasil');

INSERT INTO tb_trophy_team (team_id, trophy_id, quantity) VALUES (1, 1, 8);
INSERT INTO tb_trophy_team (team_id, trophy_id, quantity) VALUES (1, 2, 5);
INSERT INTO tb_trophy_team (team_id, trophy_id, quantity) VALUES (2, 1, 7);
INSERT INTO tb_trophy_team (team_id, trophy_id, quantity) VALUES (2, 2, 3);
