//package pe.edu.upc.matchevent.proposals.domain.model.valueobjects;
//
//import jakarta.persistence.Embeddable;
//import java.io.Serializable;
//
//@Embeddable
//public record RequestId(Long value) implements Serializable {
//    public RequestId {
//        if (value != null && value < 0)
//            throw new IllegalArgumentException("RequestId cannot be negative");
//    }
//
//    public RequestId() {
//        this(0L);
//    }
//}