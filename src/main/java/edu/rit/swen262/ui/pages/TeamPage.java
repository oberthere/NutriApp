package edu.rit.swen262.ui.pages;

import edu.rit.swen262.ui.PageData;
import edu.rit.swen262.ui.commands.AddTeamCommand;
import edu.rit.swen262.ui.commands.CreateChallengeCommand;
import edu.rit.swen262.ui.commands.LeaveTeamCommand;
import edu.rit.swen262.ui.commands.SendTeamInviteCommand;
import edu.rit.swen262.ui.commands.UserCommand;
import edu.rit.swen262.ui.commands.ViewTeamMemberWorkoutCommand;

public class TeamPage extends Page {
    private UserCommand addTeamCommand;
    private UserCommand sendTeamInviteCommand;
    private UserCommand viewMemberWorkout;
    private UserCommand createChallengeCommand;
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
        // this.createChallengeCommand = new createChallengeCommand();
        // super.userCommands.add(createChallengeCommand);
        this.leaveTeamCommand = new LeaveTeamCommand(pageData);
        super.userCommands.add(leaveTeamCommand);
    }

    @Override
    public void printContent() {
        // TODO Auto-generated method stub
        super.printContent();
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
        // createChallengeCommand.setActive(true);
        leaveTeamCommand.setActive(true);
    }

    private void hasNoTeamUpdate() {
        addTeamCommand.setActive(true);
        sendTeamInviteCommand.setActive(false);
        viewMemberWorkout.setActive(false);
        // createChallengeCommand.setActive(false);
        leaveTeamCommand.setActive(false);
    }




}
