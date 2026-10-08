package pd5.model;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ConsultantNote {
    private final long id;
    private String content;
    private final LocalDateTime createdAt;
    private final String author;
    private boolean isArchived;

    public ConsultantNote(long id, String content, LocalDateTime createdAt, String author) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.isArchived = false;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public void editNote(ConsultantNote consultantNote) {
        if (!consultantNote.isArchived()) {
            Scanner sn = new Scanner(System.in);
            String noteContent = consultantNote.getContent();
            System.out.println("Old content:");
            System.out.println(noteContent);
            System.out.println("Type new content:");
            String newContent = sn.nextLine();
            consultantNote.setContent(newContent);
            System.out.println(archiveNote(consultantNote));
            sn.close();
        } else {
            System.out.println(archiveNote(consultantNote));
        }
    }

    private String archiveNote(ConsultantNote consultantNote) {
        String result;
        if (consultantNote.isArchived()) {
            result = "This note has been already archived and cannot be changed!";
            return result;
        } else {
            archive(consultantNote);
            result = "This note has been archived successfully";
        }
        return result;
    }

    private void archive(ConsultantNote consultantNote) {
        consultantNote.setArchived(true);
    }
}