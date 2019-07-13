package com.jazwii.jazpassgen.Singleton;

public class RestViews {
    public interface PublicMinimal {
    }

    public interface PublicMinimalAll extends PublicMinimal,
            AccountPublicMinimal {
    }

    public interface PublicDetailedAll extends PublicMinimalAll,
            AccountPublicDetailed {
    }

    public interface ExtendedMinimalAll extends PublicMinimalAll {
    }

    public interface ExtendedDetailedAll extends PublicDetailedAll {
    }

    public interface InternalMinimalAll extends ExtendedMinimalAll {
    }

    public interface InternalDetailedAll extends ExtendedDetailedAll {
    }

    public interface AccountPublicMinimal extends PublicMinimal {
    }

    public interface AccountPublicDetailed extends AccountPublicMinimal {
    }

    public interface AccountCriticalDetailed extends AccountPublicDetailed {
    }
}
