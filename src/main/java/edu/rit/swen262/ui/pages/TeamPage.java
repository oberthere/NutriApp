package edu.rit.swen262.ui.pages;

import edu.rit.swen262.team.Team;
import edu.rit.swen262.team.TeamInvite;
import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.AddTeamCommand;
import edu.rit.swen262.ui.commands.CreateChallengeCommand;
import edu.rit.swen262.ui.commands.JoinTeamCommand;
import edu.rit.swen262.ui.commands.LeaveTeamCommand;
import edu.rit.swen262.ui.commands.SendTeamInviteCommand;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.commands.ViewTeamMemberWorkoutCommand;
import edu.rit.swen262.user.User;

public class TeamPage extends Page {
    private UserCommand addTeamCommand;
    private UserCommand sendTeamInviteCommand;
    private UserCommand viewMemberWorkout;
    private UserCommand createChallengeCommand;
    private UserCommand joinTeamCommand;
    private UserCommand leaveTeamCommand;

    public TeamPage(PageData pageData) {
        super(pageData);
        this.pageName = "Team Page";
        this.addTeamCommand = new AddTeamCommand(pageData);
        super.userCommands.add(addTeamCommand);
        this.createChallengeCommand = new CreateChallengeCommand(pageData);
        super.userCommands.add(createChallengeCommand);
        this.sendTeamInviteCommand = new SendTeamInviteCommand(pageData);
        super.userCommands.add(sendTeamInviteCommand);
        this.viewMemberWorkout = new ViewTeamMemberWorkoutCommand(pageData);
        super.userCommands.add(viewMemberWorkout);
        this.joinTeamCommand = new JoinTeamCommand(pageData);
        super.userCommands.add(joinTeamCommand);
        this.leaveTeamCommand = new LeaveTeamCommand(pageData);
        super.userCommands.add(leaveTeamCommand);
    }

    @Override
    public void printContent() {
        // TODO Auto-generated method stub
        super.printContent();
        if (pageData.getCurrentUser().getTeam() != null) {
            System.out.println("Team Info: ");
            Team team = pageData.getCurrentUser().getTeam();
            System.out.println("Team Name: " + team.getTeamName());
            System.out.println("   Team Members:");
            for (User user : team.getMembers()) {
                System.out.println("\t- " + user.getName());
            }

            if (team.getChallenge() != null) {
                System.out.println("\tTeam Challenge: " + 
                team.getChallenge().getName() + " " + team.getChallenge().getEndDate() + " " + team.getChallenge().getInstructions());
            }

        } else {
            System.out.println();
            System.out.println("Team Invites:");
            User user = pageData.getCurrentUser();
            if (user.getTeamInvite().size() == 0) {
                System.out.println("\tNone");
            } else {
                for (TeamInvite teamInvite : user.getTeamInvite()) {
                    System.out.println("\t- From: " + teamInvite.getTeamName());
                }
            }
        }

        

    }
    

    @Override
    public void printCommand() {
        if (pageData.getCurrentUser().getTeam() != null) {hasTeamUpdate();}
        else {hasNoTeamUpdate();}
        super.printCommand();
    }

    private void hasTeamUpdate() {
        addTeamCommand.setActive(false);
        sendTeamInviteCommand.setActive(true);
        viewMemberWorkout.setActive(true);
        createChallengeCommand.setActive(true);
        joinTeamCommand.setActive(false);
        leaveTeamCommand.setActive(true);
    }

    private void hasNoTeamUpdate() {
        addTeamCommand.setActive(true);
        sendTeamInviteCommand.setActive(false);
        viewMemberWorkout.setActive(false);
        createChallengeCommand.setActive(false);
        joinTeamCommand.setActive(true);
        leaveTeamCommand.setActive(false);
    }




}
