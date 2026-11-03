package pd10;

import pd10.model.Category;
import pd10.model.Priority;
import pd10.model.TicketStatus;
import pd10.model.User;
import pd10.repository.InMemoryTicketRepository;
import pd10.repository.InMemoryUserRepository;
import pd10.service.ReportService;
import pd10.service.ServiceDeskFacade;

public class Main {
    public static void main(String[] args) {
        InMemoryTicketRepository inMemoryTicketRepository = new InMemoryTicketRepository();
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

        ReportService reportService = new ReportService(inMemoryTicketRepository);
        ServiceDeskFacade serviceDeskService = new ServiceDeskFacade(inMemoryTicketRepository, inMemoryUserRepository);

        User adminPawel = serviceDeskService.registerUser("Pawel", "Admin");
        serviceDeskService.registerTicket(1, "title", "description", TicketStatus.NEW, Priority.LOW, Category.ADMINS, 1, 1);
        serviceDeskService.assignTicket(1, adminPawel);
        serviceDeskService.startWorkingOnTicket(1);
        serviceDeskService.closeTicket(1);
        System.out.println(serviceDeskService.getTicketDetails(1));

    }
}
