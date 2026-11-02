package pd10;

import pd10.model.Category;
import pd10.model.Priority;
import pd10.model.TicketStatus;
import pd10.model.User;
import pd10.repository.InMemoryTicketRepository;
import pd10.repository.InMemoryUserRepository;
import pd10.service.ReportService;
import pd10.service.ServiceDeskService;

public class Main {
    public static void main(String[] args) {
        InMemoryTicketRepository inMemoryTicketRepository = new InMemoryTicketRepository();
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

        ReportService reportService = new ReportService(inMemoryTicketRepository);
        ServiceDeskService serviceDeskService = new ServiceDeskService(inMemoryTicketRepository, inMemoryUserRepository);

        User adminPawel = serviceDeskService.registerUser("Pawel", "Admin");
        serviceDeskService.registerTicket(1,"title","description", TicketStatus.NEW, Priority.LOW, Category.ADMINS, adminPawel,adminPawel);
        serviceDeskService.assignTicket(1,adminPawel);
        serviceDeskService.startWorkingOnTicket(1);
        serviceDeskService.closeTicket(1);
        System.out.println(serviceDeskService.getTicketDetails(1));


    }
}
